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

			Long totalEstudiantes = inscripcionRepository.countEstudiantesUnicosInscritos();
			dashboard.setTotalEstudiantesActivos(totalEstudiantes != null ? totalEstudiantes : 0L);

			Long totalMaterias = materiaRepository.count();
			dashboard.setTotalMateriasOfertadas(totalMaterias != null ? totalMaterias : 0L);

			List<MateriaStatsDTO> metricas = inscripcionRepository.countInscritosPorMateria();
			if (metricas == null || metricas.isEmpty()) {
				dashboard.setMetricasMaterias(new ArrayList<>());
			} else {
				dashboard.setMetricasMaterias(metricas);
			}

		} catch (Exception e) {

			dashboard.setTotalEstudiantesActivos(0L);
			dashboard.setTotalMateriasOfertadas(0L);
			dashboard.setMetricasMaterias(new ArrayList<>());
		}

		return dashboard;
	}
}