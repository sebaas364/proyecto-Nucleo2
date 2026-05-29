package co.edu.unbosque.sipeh.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import co.edu.unbosque.sipeh.dto.DecanoDashboardDTO;
import co.edu.unbosque.sipeh.repository.InscripcionRepository;
import co.edu.unbosque.sipeh.repository.MateriaRepository;

@ExtendWith(MockitoExtension.class)
public class ReporteServiceTest {

	@Mock
	private InscripcionRepository inscripcionRepository;

	@Mock
	private MateriaRepository materiaRepository;

	@InjectMocks
	private ReporteService reporteService;

	@Test
	void testObtenerEstadisticasDecano() {
		when(inscripcionRepository.countEstudiantesUnicosInscritos()).thenReturn(150L);
		when(materiaRepository.count()).thenReturn(20L);
		when(inscripcionRepository.countInscritosPorMateria()).thenReturn(new ArrayList<>());

		DecanoDashboardDTO resultado = reporteService.obtenerEstadisticasDecano();

		assertNotNull(resultado);
		assertEquals(150L, resultado.getTotalEstudiantesActivos());
		assertEquals(20L, resultado.getTotalMateriasOfertadas());
	}
}