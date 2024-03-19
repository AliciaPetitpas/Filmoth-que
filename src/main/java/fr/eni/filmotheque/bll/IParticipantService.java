package fr.eni.filmotheque.bll;

import fr.eni.filmotheque.bo.Participant;

import java.util.List;
import java.util.Optional;

public interface IParticipantService {
    List<Participant> consulterParticipants();

    Optional<Participant> consulterParticipantParId(long id);
}
