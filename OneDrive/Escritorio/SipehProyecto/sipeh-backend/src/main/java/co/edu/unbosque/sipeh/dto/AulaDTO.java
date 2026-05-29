package co.edu.unbosque.sipeh.dto;

public class AulaDTO {

	private Long id;
	private String nombre;
	private Integer capacidad;
	private String caracteristicas;

	public AulaDTO() {
	}

	public AulaDTO(Long id, String nombre, Integer capacidad, String caracteristicas) {
		this.id = id;
		this.nombre = nombre;
		this.capacidad = capacidad;
		this.caracteristicas = caracteristicas;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}

	public String getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(String caracteristicas) {
		this.caracteristicas = caracteristicas;
	}
}