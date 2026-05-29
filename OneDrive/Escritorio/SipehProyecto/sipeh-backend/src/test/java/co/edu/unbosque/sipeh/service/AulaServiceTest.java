package co.edu.unbosque.sipeh.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import co.edu.unbosque.sipeh.dto.AulaDTO;
import co.edu.unbosque.sipeh.model.Aula;
import co.edu.unbosque.sipeh.repository.AulaRepository;

@ExtendWith(MockitoExtension.class)
public class AulaServiceTest {

	@Mock
	private AulaRepository aulaRepository;

	@InjectMocks
	private AulaService aulaService;

	@Test
	void testListarAulas_RetornarListaDeAulaDTOs() {

		Aula aulaMock = new Aula();
		aulaMock.setId(1L);
		aulaMock.setNombre("LAB-DEV-01");
		aulaMock.setCapacidad(35);
		aulaMock.setCaracteristicas("PCs Core i7");

		when(aulaRepository.findAll()).thenReturn(Arrays.asList(aulaMock));

		List<AulaDTO> resultado = aulaService.listarAulas();

		assertNotNull(resultado, "La lista de aulas no debería ser nula");
		assertEquals(1, resultado.size(), "Debería haber exactamente 1 aula en la lista");

		assertEquals("LAB-DEV-01", resultado.get(0).getNombre());
		assertEquals(35, resultado.get(0).getCapacidad());

		verify(aulaRepository, times(1)).findAll();
	}

	@Test
	void testGuardarAula_MapearYGuardarCorrectamente() {

		AulaDTO inputDto = new AulaDTO(null, "Aula 401", 40, "Video Beam");

		Aula aulaGuardadaMock = new Aula();
		aulaGuardadaMock.setId(10L);
		aulaGuardadaMock.setNombre("Aula 401");
		aulaGuardadaMock.setCapacidad(40);
		aulaGuardadaMock.setCaracteristicas("Video Beam");

		when(aulaRepository.save(any(Aula.class))).thenReturn(aulaGuardadaMock);

		AulaDTO resultado = aulaService.guardarAula(inputDto);

		assertNotNull(resultado);
		assertEquals(10L, resultado.getId(), "El ID debe ser el generado por la base de datos");
		assertEquals("Aula 401", resultado.getNombre());

		verify(aulaRepository, times(1)).save(any(Aula.class));
	}

	@Test
	void testEliminarAula_LlamarAlRepositorio() {

		Long idAulaAEliminar = 1L;

		doNothing().when(aulaRepository).deleteById(idAulaAEliminar);

		aulaService.eliminarAula(idAulaAEliminar);

		verify(aulaRepository, times(1)).deleteById(idAulaAEliminar);
	}
}