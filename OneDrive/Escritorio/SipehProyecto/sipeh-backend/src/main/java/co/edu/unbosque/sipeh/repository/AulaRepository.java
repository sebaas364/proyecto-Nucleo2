package co.edu.unbosque.sipeh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import co.edu.unbosque.sipeh.model.Aula;

@Repository
public interface AulaRepository extends JpaRepository<Aula, Long> {
}