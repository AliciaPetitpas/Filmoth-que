package fr.eni.filmotheque.bll.jpa;

import fr.eni.filmotheque.bll.IParticipantService;
import fr.eni.filmotheque.bo.Participant;
import fr.eni.filmotheque.dal.ParticipantJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Profile("prod")
public class ParticipantServiceJpaImpl implements IParticipantService {
    @Autowired
    private ParticipantJpaRepository participantJpaRepository;

    public ParticipantServiceJpaImpl(ParticipantJpaRepository participantJpaRepository) {
        this.participantJpaRepository = participantJpaRepository;

        if (participantJpaRepository.findAll().size() == 0) {
            enregistrerParticipant(new Participant("Spielberg", "Steven"));
            enregistrerParticipant(new Participant("Cronenberg", "David"));
            enregistrerParticipant(new Participant("Boon", "Dany"));
            enregistrerParticipant(new Participant("Attenborough", "Richard"));
            enregistrerParticipant(new Participant("Davis", "Geena"));
            enregistrerParticipant(new Participant("Rylance", "Mark"));
            enregistrerParticipant(new Participant("Merad", "Kad"));
        }
    }

    @Override
    public List<Participant> consulterParticipants() {
        return participantJpaRepository.findAll();
    }

    @Override
    public Participant consulterParticipantParId(long id) {
        return participantJpaRepository.findById(id).orElse(null);
    }

    @Override
    public void enregistrerParticipant(Participant participant) {
        participantJpaRepository.save(participant);
    }

    @Override
    public void supprimerParticipantParId(long id) {
        participantJpaRepository.deleteById(id);
    }
}
