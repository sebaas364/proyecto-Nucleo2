package co.edu.unbosque.sipeh.service;

import co.edu.unbosque.sipeh.dto.MateriaDTO;
import co.edu.unbosque.sipeh.model.Materia;
import co.edu.unbosque.sipeh.model.Usuario;
import co.edu.unbosque.sipeh.repository.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MateriaService {

	
	@Autowired
	private MateriaRepository materiaRepository;

	public List<MateriaDTO> listarMaterias() {
		return materiaRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	public MateriaDTO guardarMateria(MateriaDTO dto) {
		Materia materia = convertToEntity(dto);
		Materia materiaGuardada = materiaRepository.save(materia);
		return convertToDTO(materiaGuardada);
	}

	public void eliminarMateria(Long id) {
		materiaRepository.deleteById(id);
	}

	
	private MateriaDTO convertToDTO(Materia materia) {
		Long docenteId = (materia.getDocente() != null) ? materia.getDocente().getId() : null;
		return new MateriaDTO(materia.getId(), materia.getCodigo(), materia.getNombre(), materia.getCreditos(),
				materia.getCupos(), materia.getGrupo(), materia.getHorario(), materia.getFrecuenciaSemanal(),
				materia.getRequisitosAula(), docenteId);
	}

	private Materia convertToEntity(MateriaDTO dto) {
		Materia materia = new Materia();
		materia.setId(dto.getId());
		materia.setCodigo(dto.getCodigo());
		materia.setNombre(dto.getNombre());
		materia.setCreditos(dto.getCreditos());
		materia.setCupos(dto.getCupos());
		materia.setGrupo(dto.getGrupo());
		materia.setHorario(dto.getHorario());

		if (dto.getFrecuenciaSemanal() != null) {
			materia.setFrecuenciaSemanal(dto.getFrecuenciaSemanal());
		} else {
			materia.setFrecuenciaSemanal(1);
		}
		materia.setRequisitosAula(dto.getRequisitosAula());

		if (dto.getDocenteId() != null) {
			Usuario docente = new Usuario();
			docente.setId(dto.getDocenteId());
			materia.setDocente(docente);
		}
		return materia;
	}
}