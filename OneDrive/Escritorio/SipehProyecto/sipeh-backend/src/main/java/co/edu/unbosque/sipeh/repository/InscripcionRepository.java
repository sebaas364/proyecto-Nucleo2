package co.edu.unbosque.sipeh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.edu.unbosque.sipeh.dto.MateriaStatsDTO;
import co.edu.unbosque.sipeh.model.Inscripcion;

@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {

	List<Inscripcion> findByEstudianteId(Long estudianteId);

	List<Inscripcion> findByMateriaId(Long materiaId);
	
	@Query("SELECT COUNT(DISTINCT i.estudiante.id) FROM Inscripcion i")
    Long countEstudiantesUnicosInscritos();

    // 👇 AQUÍ ESTÁ LA CORRECCIÓN: La ruta exacta de tu proyecto
    @Query("SELECT new co.edu.unbosque.sipeh.dto.MateriaStatsDTO(m.nombre, COUNT(i.id)) " +
           "FROM Inscripcion i JOIN i.materia m GROUP BY m.nombre ORDER BY COUNT(i.id) DESC")
    List<MateriaStatsDTO> countInscritosPorMateria();
}