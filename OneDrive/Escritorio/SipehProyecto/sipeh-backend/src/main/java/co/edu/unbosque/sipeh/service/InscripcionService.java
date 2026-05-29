package co.edu.unbosque.sipeh.service;

import co.edu.unbosque.sipeh.dto.InscripcionDTO;
import co.edu.unbosque.sipeh.model.Inscripcion;
import co.edu.unbosque.sipeh.model.Materia;
import co.edu.unbosque.sipeh.model.Usuario;
import co.edu.unbosque.sipeh.repository.InscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InscripcionService {

	@Autowired
	private InscripcionRepository inscripcionRepository;

	@Transactional(readOnly = true)
	public List<InscripcionDTO> obtenerPorEstudiante(Long id) {
		return inscripcionRepository.findByEstudianteId(id).stream().map(this::convertToDTO)
				.collect(Collectors.toList());
	}

	public List<InscripcionDTO> obtenerPorMateria(Long id) {
		return inscripcionRepository.findByMateriaId(id).stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	public InscripcionDTO inscribir(InscripcionDTO dto) {
		Inscripcion inscripcion = new Inscripcion();

		if (dto.getEstudianteId() != null) {
			Usuario estudiante = new Usuario();
			estudiante.setId(dto.getEstudianteId());
			inscripcion.setEstudiante(estudiante);
		}

		if (dto.getMateriaId() != null) {
			Materia materia = new Materia();
			materia.setId(dto.getMateriaId());
			inscripcion.setMateria(materia);
		}

		Inscripcion inscripcionGuardada = inscripcionRepository.save(inscripcion);
		return convertToDTO(inscripcionGuardada);
	}

	public void cancelarInscripcion(Long id) {
		inscripcionRepository.deleteById(id);
	}

	private InscripcionDTO convertToDTO(Inscripcion inscripcion) {
		Long estudianteId = (inscripcion.getEstudiante() != null) ? inscripcion.getEstudiante().getId() : null;
		Long materiaId = (inscripcion.getMateria() != null) ? inscripcion.getMateria().getId() : null;
		return new InscripcionDTO(inscripcion.getId(), estudianteId, materiaId);
	}
}