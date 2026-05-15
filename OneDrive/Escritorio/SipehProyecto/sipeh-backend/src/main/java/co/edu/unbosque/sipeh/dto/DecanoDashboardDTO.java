package co.edu.unbosque.sipeh.dto;

import java.util.List;

public class DecanoDashboardDTO {
	private Long totalEstudiantesActivos;
	private Long totalMateriasOfertadas;
	
	private List<MateriaStatsDTO> metricasMaterias;

	public Long getTotalEstudiantesActivos() {
		return totalEstudiantesActivos;
	}

	public void setTotalEstudiantesActivos(Long totalEstudiantesActivos) {
		this.totalEstudiantesActivos = totalEstudiantesActivos;
	}

	public Long getTotalMateriasOfertadas() {
		return totalMateriasOfertadas;
	}

	public void setTotalMateriasOfertadas(Long totalMateriasOfertadas) {
		this.totalMateriasOfertadas = totalMateriasOfertadas;
	}

	public List<MateriaStatsDTO> getMetricasMaterias() {
		return metricasMaterias;
	}

	public void setMetricasMaterias(List<MateriaStatsDTO> metricasMaterias) {
		this.metricasMaterias = metricasMaterias;
	}

}