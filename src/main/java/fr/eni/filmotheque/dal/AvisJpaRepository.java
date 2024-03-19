package fr.eni.filmotheque.dal;

import fr.eni.filmotheque.bo.Avis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvisJpaRepository extends JpaRepository<Avis, Long> {
}
