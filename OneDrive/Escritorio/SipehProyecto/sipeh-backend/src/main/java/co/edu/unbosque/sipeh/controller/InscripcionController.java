package co.edu.unbosque.sipeh.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.edu.unbosque.sipeh.dto.InscripcionDTO;
import co.edu.unbosque.sipeh.service.InscripcionService;

@RestController
@RequestMapping("/api/inscripciones")
public class InscripcionController {

	private final InscripcionService inscripcionService;

	public InscripcionController(InscripcionService inscripcionService) {
		this.inscripcionService = inscripcionService;
	}

	@GetMapping("/estudiante/{id}")
	public ResponseEntity<List<InscripcionDTO>> obtenerPorEstudiante(@PathVariable Long id) {
		return ResponseEntity.ok(inscripcionService.obtenerPorEstudiante(id));
	}

	@GetMapping("/materia/{id}")
	public ResponseEntity<List<InscripcionDTO>> obtenerPorMateria(@PathVariable Long id) {
		return ResponseEntity.ok(inscripcionService.obtenerPorMateria(id));
	}

	@PostMapping
	public ResponseEntity<InscripcionDTO> inscribir(@RequestBody InscripcionDTO dto) {
		return ResponseEntity.ok(inscripcionService.inscribir(dto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> cancelarInscripcion(@PathVariable Long id) {
		inscripcionService.cancelarInscripcion(id);
		return ResponseEntity.noContent().build();
	}
}