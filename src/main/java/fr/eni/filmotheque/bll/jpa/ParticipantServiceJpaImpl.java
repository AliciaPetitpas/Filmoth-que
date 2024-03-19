package fr.eni.filmotheque.bll.jpa;

import fr.eni.filmotheque.bll.IParticipantService;
import fr.eni.filmotheque.bo.Participant;
import fr.eni.filmotheque.dal.ParticipantJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParticipantServiceJpaImpl implements IParticipantService {
    @Autowired
    private ParticipantJpaRepository participantJpaRepository;

    @Override
    public List<Participant> consulterParticipants() {
        return participantJpaRepository.findAll();
    }

    @Override
    public Optional<Participant> consulterParticipantParId(long id) {
        return participantJpaRepository.findById(id);
    }
}
