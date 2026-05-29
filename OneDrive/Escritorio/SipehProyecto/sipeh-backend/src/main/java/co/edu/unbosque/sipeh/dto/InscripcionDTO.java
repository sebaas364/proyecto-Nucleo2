package co.edu.unbosque.sipeh.dto;

public class InscripcionDTO {

	private Long id;
	private Long estudianteId;
	private Long materiaId;

	public InscripcionDTO() {
	}

	public InscripcionDTO(Long id, Long estudianteId, Long materiaId) {
		this.id = id;
		this.estudianteId = estudianteId;
		this.materiaId = materiaId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getEstudianteId() {
		return estudianteId;
	}

	public void setEstudianteId(Long estudianteId) {
		this.estudianteId = estudianteId;
	}

	public Long getMateriaId() {
		return materiaId;
	}

	public void setMateriaId(Long materiaId) {
		this.materiaId = materiaId;
	}
}