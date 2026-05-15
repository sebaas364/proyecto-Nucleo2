package co.edu.unbosque.sipeh.dto;

public class MateriaStatsDTO {
	private String nombreMateria;
	private Long totalInscritos;

	public MateriaStatsDTO(String nombreMateria, Long totalInscritos) {
		this.nombreMateria = nombreMateria;
		this.totalInscritos = totalInscritos;
	}

	public String getNombreMateria() {
		return nombreMateria;
	}

	public void setNombreMateria(String nombreMateria) {
		this.nombreMateria = nombreMateria;
	}

	public Long getTotalInscritos() {
		return totalInscritos;
	}

	public void setTotalInscritos(Long totalInscritos) {
		this.totalInscritos = totalInscritos;
	}

}