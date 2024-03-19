package fr.eni.filmotheque.dal;

import fr.eni.filmotheque.bo.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantJpaRepository extends JpaRepository<Participant, Long> {
}
