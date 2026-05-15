package co.edu.unbosque.sipeh.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "materias")
public class Materia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true, length = 20)
	private String codigo;

	@Column(nullable = false, length = 100)
	private String nombre;

	@Column(nullable = false)
	private Integer creditos;

	@Column(nullable = false)
	private Integer cupos;

	@Column(length = 10)
	private String grupo;

	@Column(length = 50)
	private String horario;

	@Column(nullable = false, columnDefinition = "integer default 1")
	private Integer frecuenciaSemanal;

	@Column(length = 100)
	private String requisitosAula;
	
	@ManyToOne
	@JoinColumn(name = "docente_id")
	private Usuario docente;

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

	public Usuario getDocente() {
		return docente;
	}

	public void setDocente(Usuario docente) {
		this.docente = docente;
	}
	

}