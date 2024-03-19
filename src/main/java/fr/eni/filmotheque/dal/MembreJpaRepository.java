package fr.eni.filmotheque.dal;

import fr.eni.filmotheque.bo.Membre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembreJpaRepository extends JpaRepository<Membre, Long> {
}
