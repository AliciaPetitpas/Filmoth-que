package fr.eni.filmotheque.bll;

import fr.eni.filmotheque.bo.Participant;

import java.util.List;
import java.util.Optional;

public interface IParticipantService {
    List<Participant> consulterParticipants();

    Participant consulterParticipantParId(long id);

    void enregistrerParticipant(Participant participant);

    void supprimerParticipantParId(long id);
}
