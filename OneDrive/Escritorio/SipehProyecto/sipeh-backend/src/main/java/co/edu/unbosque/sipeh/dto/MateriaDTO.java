package co.edu.unbosque.sipeh.dto;

public class MateriaDTO {

	private Long id;
	private String codigo;
	private String nombre;
	private Integer creditos;
	private Integer cupos;
	private String grupo;
	private String horario;
	private Integer frecuenciaSemanal;
	private String requisitosAula;
	private Long docenteId; 

	public MateriaDTO() {
	}

	public MateriaDTO(Long id, String codigo, String nombre, Integer creditos, Integer cupos, String grupo,
			String horario, Integer frecuenciaSemanal, String requisitosAula, Long docenteId) {
		this.id = id;
		this.codigo = codigo;
		this.nombre = nombre;
		this.creditos = creditos;
		this.cupos = cupos;
		this.grupo = grupo;
		this.horario = horario;
		this.frecuenciaSemanal = frecuenciaSemanal;
		this.requisitosAula = requisitosAula;
		this.docenteId = docenteId;
	}

	// --- GETTERS Y SETTERS ---
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getCreditos() {
		return creditos;
	}

	public void setCreditos(Integer creditos) {
		this.creditos = creditos;
	}

	public Integer getCupos() {
		return cupos;
	}

	public void setCupos(Integer cupos) {
		this.cupos = cupos;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public Integer getFrecuenciaSemanal() {
		return frecuenciaSemanal;
	}

	public void setFrecuenciaSemanal(Integer frecuenciaSemanal) {
		this.frecuenciaSemanal = frecuenciaSemanal;
	}

	public String getRequisitosAula() {
		return requisitosAula;
	}

	public void setRequisitosAula(String requisitosAula) {
		this.requisitosAula = requisitosAula;
	}

	public Long getDocenteId() {
		return docenteId;
	}

	public void setDocenteId(Long docenteId) {
		this.docenteId = docenteId;
	}
}
