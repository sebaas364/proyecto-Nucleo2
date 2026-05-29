package co.edu.unbosque.sipeh.service;

import co.edu.unbosque.sipeh.dto.AulaDTO;
import co.edu.unbosque.sipeh.model.Aula;
import co.edu.unbosque.sipeh.repository.AulaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AulaService {

	private final AulaRepository aulaRepository;

	public AulaService(AulaRepository aulaRepository) {
		this.aulaRepository = aulaRepository;
	}

	public List<AulaDTO> listarAulas() {
		return aulaRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	public AulaDTO guardarAula(AulaDTO dto) {
		Aula aula = convertToEntity(dto);
		Aula aulaGuardada = aulaRepository.save(aula);
		return convertToDTO(aulaGuardada);
	}

	public void eliminarAula(Long id) {
		aulaRepository.deleteById(id);
	}

	private AulaDTO convertToDTO(Aula aula) {
		return new AulaDTO(aula.getId(), aula.getNombre(), aula.getCapacidad(), aula.getCaracteristicas());
	}

	private Aula convertToEntity(AulaDTO dto) {
		Aula aula = new Aula();
		aula.setId(dto.getId());
		aula.setNombre(dto.getNombre());
		aula.setCapacidad(dto.getCapacidad());
		aula.setCaracteristicas(dto.getCaracteristicas());
		return aula;
	}
}