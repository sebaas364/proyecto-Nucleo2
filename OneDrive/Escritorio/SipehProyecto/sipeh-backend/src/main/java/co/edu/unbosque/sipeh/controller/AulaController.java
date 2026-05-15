package co.edu.unbosque.sipeh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.sipeh.model.Aula;
import co.edu.unbosque.sipeh.repository.AulaRepository;

@RestController
@RequestMapping("/api/aulas")
@CrossOrigin(origins = "http://localhost:4200")
public class AulaController {

	@Autowired
	private AulaRepository aulaRepository;

	@GetMapping
	public List<Aula> listarAulas() {
		return aulaRepository.findAll();
	}

	@PostMapping
	public Aula guardarAula(@RequestBody Aula aula) {
		return aulaRepository.save(aula);
	}
	@DeleteMapping("/{id}")
	public void eliminar(@PathVariable Long id) { aulaRepository.deleteById(id); }
}