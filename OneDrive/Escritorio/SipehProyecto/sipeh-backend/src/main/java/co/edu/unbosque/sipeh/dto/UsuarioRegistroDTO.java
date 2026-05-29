package co.edu.unbosque.sipeh.dto;

public class UsuarioRegistroDTO {
    private Long id;
    private String nombre;
    private String email;
    private String password;
    private String rol;
    private String vinculacion;
    private String escalafon;
    private String restriccionesHorario;

    // --- Getters y Setters ---
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getVinculacion() {
        return vinculacion;
    }

    public void setVinculacion(String vinculacion) {
        this.vinculacion = vinculacion;
    }

    public String getEscalafon() {
        return escalafon;
    }

    public void setEscalafon(String escalafon) {
        this.escalafon = escalafon;
    }

    public String getRestriccionesHorario() {
        return restriccionesHorario;
    }

    public void setRestriccionesHorario(String restriccionesHorario) {
        this.restriccionesHorario = restriccionesHorario;
    }
}