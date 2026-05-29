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

import co.edu.unbosque.sipeh.dto.InscripcionDTO;
import co.edu.unbosque.sipeh.model.Inscripcion;
import co.edu.unbosque.sipeh.repository.InscripcionRepository;

@ExtendWith(MockitoExtension.class)
public class InscripcionServiceTest {

	@Mock
	private InscripcionRepository inscripcionRepository;

	@InjectMocks
	private InscripcionService inscripcionService;

	@Test
	void testObtenerPorEstudiante() {
		Inscripcion inscripcionMock = new Inscripcion();
		inscripcionMock.setId(1L);

		when(inscripcionRepository.findByEstudianteId(100L)).thenReturn(Arrays.asList(inscripcionMock));

		List<InscripcionDTO> resultado = inscripcionService.obtenerPorEstudiante(100L);

		assertNotNull(resultado);
		assertEquals(1, resultado.size());
		verify(inscripcionRepository, times(1)).findByEstudianteId(100L);
	}
}