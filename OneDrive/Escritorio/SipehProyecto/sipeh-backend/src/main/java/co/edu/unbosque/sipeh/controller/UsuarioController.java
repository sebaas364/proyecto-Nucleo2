package co.edu.unbosque.sipeh.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.sipeh.model.Usuario;
import co.edu.unbosque.sipeh.repository.UsuarioRepository;

@RestController
@RequestMapping("/api/usuarios")

public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping
	public List<Usuario> listarUsuarios() {
		return usuarioRepository.findAll();
	}

	@PostMapping
	public Usuario guardarUsuario(@RequestBody Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	// Obtener SOLO los docentes (US-08)
    @GetMapping("/docentes")
    public List<Usuario> listarDocentes() {
        return usuarioRepository.findByRol("DOCENTE");
    }

    // Registrar un nuevo docente (US-05)
    @PostMapping("/docentes")
    public Usuario guardarDocente(@RequestBody Usuario docente) {
        docente.setRol("DOCENTE"); // Aseguramos que nadie le cambie el rol
        return usuarioRepository.save(docente);
    }
    
    // Eliminar docente
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
    }

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Usuario datosLogin) {
		Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(datosLogin.getEmail());

		if (usuarioExistente.isPresent()) {
			Usuario user = usuarioExistente.get();
			if (user.getPassword().equals(datosLogin.getPassword())) {
				return ResponseEntity.ok(user);
			} else {
				return ResponseEntity.status(401).body("Contraseña incorrecta");
			}
		}
		return ResponseEntity.status(404).body("Usuario no encontrado");
	}
}