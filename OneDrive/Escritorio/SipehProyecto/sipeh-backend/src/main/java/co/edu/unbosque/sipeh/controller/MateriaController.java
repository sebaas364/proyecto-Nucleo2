package co.edu.unbosque.sipeh.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.edu.unbosque.sipeh.dto.MateriaDTO;
import co.edu.unbosque.sipeh.service.MateriaService;

@RestController
@RequestMapping("/api/materias")
public class MateriaController {


	@Autowired
	private MateriaService materiaService;

	@GetMapping
	public ResponseEntity<List<MateriaDTO>> listarMaterias() {
		return ResponseEntity.ok(materiaService.listarMaterias());
	}

	@PostMapping
	public ResponseEntity<MateriaDTO> guardarMateria(@RequestBody MateriaDTO materiaDTO) {
		return ResponseEntity.ok(materiaService.guardarMateria(materiaDTO));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Long id) {
		materiaService.eliminarMateria(id);
		return ResponseEntity.noContent().build();
	}
}