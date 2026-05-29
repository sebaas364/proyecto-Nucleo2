package co.edu.unbosque.sipeh.service;

import co.edu.unbosque.sipeh.dto.DecanoDashboardDTO;
import co.edu.unbosque.sipeh.dto.MateriaStatsDTO;
import co.edu.unbosque.sipeh.repository.InscripcionRepository;
import co.edu.unbosque.sipeh.repository.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReporteService {

	@Autowired
	private InscripcionRepository inscripcionRepository;

	@Autowired
	private MateriaRepository materiaRepository;

	public DecanoDashboardDTO obtenerEstadisticasDecano() {
		DecanoDashboardDTO dashboard = new DecanoDashboardDTO();

		try {
		
			long totalEstudiantes = inscripcionRepository.countEstudiantesUnicosInscritos();
			dashboard.setTotalEstudiantesActivos(totalEstudiantes);

			long totalMaterias = materiaRepository.count();
			dashboard.setTotalMateriasOfertadas(totalMaterias);

			
			List<MateriaStatsDTO> metricas = inscripcionRepository.countInscritosPorMateria();
			dashboard.setMetricasMaterias(metricas);

		} catch (Exception e) {
			dashboard.setTotalEstudiantesActivos(0L);
			dashboard.setTotalMateriasOfertadas(0L);
			dashboard.setMetricasMaterias(new ArrayList<>());
		}

		return dashboard;
	}
}