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

import co.edu.unbosque.sipeh.dto.MateriaDTO;
import co.edu.unbosque.sipeh.model.Materia;
import co.edu.unbosque.sipeh.repository.MateriaRepository;

@ExtendWith(MockitoExtension.class)
public class MateriaServiceTest {

	@Mock
	private MateriaRepository materiaRepository;

	@InjectMocks
	private MateriaService materiaService;

	@Test
	void testListarMaterias() {
		Materia materiaMock = new Materia();
		materiaMock.setId(1L);
		materiaMock.setCodigo("IS-301");
		materiaMock.setNombre("Estructuras de Datos");
		materiaMock.setCreditos(3);
		materiaMock.setCupos(35);

		when(materiaRepository.findAll()).thenReturn(Arrays.asList(materiaMock));

		List<MateriaDTO> resultado = materiaService.listarMaterias();

		assertNotNull(resultado);
		assertEquals(1, resultado.size());
		assertEquals("Estructuras de Datos", resultado.get(0).getNombre());
		verify(materiaRepository, times(1)).findAll();
	}
}