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

import co.edu.unbosque.sipeh.model.Materia;
import co.edu.unbosque.sipeh.repository.MateriaRepository;

@RestController
@RequestMapping("/api/materias")
public class MateriaController {

	@Autowired
	private MateriaRepository materiaRepository;

	// Traer todas las materias
	@GetMapping
	public List<Materia> listarMaterias() {
		return materiaRepository.findAll();
	}

	// Crear una nueva materia
	@PostMapping
	public Materia guardarMateria(@RequestBody Materia materia) {
		return materiaRepository.save(materia);
	}
	@DeleteMapping("/{id}")
	public void eliminar(@PathVariable Long id) { materiaRepository.deleteById(id); }
}