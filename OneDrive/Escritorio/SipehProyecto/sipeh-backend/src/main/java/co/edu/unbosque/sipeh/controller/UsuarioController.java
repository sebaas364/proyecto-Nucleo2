package co.edu.unbosque.sipeh.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.sipeh.model.Usuario;
import co.edu.unbosque.sipeh.repository.UsuarioRepository;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://localhost:4200")
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