package co.edu.unbosque.sipeh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import co.edu.unbosque.sipeh.model.Materia;

@Repository
public interface MateriaRepository extends JpaRepository<Materia, Long> {
}