package co.edu.unbosque.sipeh.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.edu.unbosque.sipeh.dto.LoginDTO;
import co.edu.unbosque.sipeh.dto.UsuarioDTO;
import co.edu.unbosque.sipeh.dto.UsuarioRegistroDTO;
import co.edu.unbosque.sipeh.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> listarUsuarios() {
		return ResponseEntity.ok(usuarioService.listarUsuarios());
	}

	@PostMapping
	public ResponseEntity<UsuarioDTO> guardarUsuario(@RequestBody UsuarioRegistroDTO dto) {
		return ResponseEntity.ok(usuarioService.guardarUsuario(dto));
	}

	@GetMapping("/docentes")
	public ResponseEntity<List<UsuarioDTO>> listarDocentes() {
		return ResponseEntity.ok(usuarioService.listarDocentes());
	}

	@PostMapping("/docentes")
	public ResponseEntity<UsuarioDTO> guardarDocente(@RequestBody UsuarioRegistroDTO dto) {
		return ResponseEntity.ok(usuarioService.guardarDocente(dto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Long id) {
		usuarioService.eliminarUsuario(id);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginDTO datosLogin) {
		try {
			UsuarioDTO usuarioLogueado = usuarioService.login(datosLogin);
			return ResponseEntity.ok(usuarioLogueado);
		} catch (RuntimeException e) {
			if (e.getMessage().equals("Contraseña incorrecta")) {
				return ResponseEntity.status(401).body(e.getMessage());
			}
			return ResponseEntity.status(404).body(e.getMessage());
		}
	}
}