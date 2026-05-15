package co.edu.unbosque.sipeh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.sipeh.model.Inscripcion;
import co.edu.unbosque.sipeh.repository.InscripcionRepository;

@RestController
@RequestMapping("/api/inscripciones")
public class InscripcionController {

	@Autowired
	private InscripcionRepository inscripcionRepository;

	// Para el Estudiante: ver sus materias inscritas
	@GetMapping("/estudiante/{id}")
	public List<Inscripcion> obtenerPorEstudiante(@PathVariable Long id) {
		return inscripcionRepository.findByEstudianteId(id);
	}

	// Para el Docente: ver qué alumnos están en su clase
	@GetMapping("/materia/{id}")
	public List<Inscripcion> obtenerPorMateria(@PathVariable Long id) {
		return inscripcionRepository.findByMateriaId(id);
	}

	// Guardar una nueva inscripción
	@PostMapping
	public Inscripcion inscribir(@RequestBody Inscripcion inscripcion) {
		return inscripcionRepository.save(inscripcion);
	}

	// Cancelar una inscripción
	@DeleteMapping("/{id}")
	public void cancelarInscripcion(@PathVariable Long id) {
		inscripcionRepository.deleteById(id);
	}
}