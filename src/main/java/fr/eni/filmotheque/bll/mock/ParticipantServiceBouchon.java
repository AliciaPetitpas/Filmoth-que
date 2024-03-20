package fr.eni.filmotheque.bll.mock;

import fr.eni.filmotheque.bll.IParticipantService;
import fr.eni.filmotheque.bo.Participant;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile("dev")
public class ParticipantServiceBouchon implements IParticipantService {
    private List<Participant> listeParticipants = new ArrayList<>();

    /*
     * On initialise la liste des participants
     */
    public ParticipantServiceBouchon() {
        enregistrerParticipant(new Participant("Spielberg", "Steven"));
        enregistrerParticipant(new Participant("Cronenberg", "David"));
        enregistrerParticipant(new Participant("Boon", "Dany"));
        enregistrerParticipant(new Participant("Attenborough", "Richard"));
        enregistrerParticipant(new Participant("Davis", "Geena"));
        enregistrerParticipant(new Participant("Rylance", "Mark"));
        enregistrerParticipant(new Participant("Merad", "Kad"));
    }

    // on gère un compteur pour l'id (qui simule l'auto-increment de la base de donnée)
    private long idCourant = 1;

    @Override
    public List<Participant> consulterParticipants() {
        return listeParticipants;
    }

    @Override
    public Participant consulterParticipantParId(long id) {
        // on recherche dans la liste le participant d'id "id"
        for (Participant participant : listeParticipants) {
            if (participant.getId() == id){
                return participant;
            }
        }
        // on retourne null si pas trouvé
        return null;
    }

    @Override
    public void supprimerParticipantParId(long id) {
        // on recherche dans la liste l'index correspondant au participant d'id "id"
        for (int index = 0; index < listeParticipants.size(); index++) {
            if (listeParticipants.get(index).getId() == id){
                listeParticipants.remove(index);
            }
        }
    }

    @Override
    public void enregistrerParticipant(Participant participant) {
        // on rajoute l'id au participant (tout en l'incrémentant)
        participant.setId(idCourant++);
        // on rajoute le participant à la liste
        listeParticipants.add(participant);
    }
}
