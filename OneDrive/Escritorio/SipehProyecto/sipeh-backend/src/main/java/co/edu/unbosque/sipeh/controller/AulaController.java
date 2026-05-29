package co.edu.unbosque.sipeh.controller;

import co.edu.unbosque.sipeh.dto.AulaDTO;
import co.edu.unbosque.sipeh.service.AulaService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/aulas")
public class AulaController {

	private final AulaService aulaService;

	public AulaController(AulaService aulaService) {
		this.aulaService = aulaService;
	}

	@GetMapping
	public ResponseEntity<List<AulaDTO>> listarAulas() {
		return ResponseEntity.ok(aulaService.listarAulas());
	}

	@PostMapping
	public ResponseEntity<AulaDTO> guardarAula(@RequestBody AulaDTO aulaDTO) {
		return ResponseEntity.ok(aulaService.guardarAula(aulaDTO));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Long id) {
		aulaService.eliminarAula(id);
		return ResponseEntity.noContent().build();
	}
}