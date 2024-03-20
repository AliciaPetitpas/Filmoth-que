package fr.eni.filmotheque.converter;

import fr.eni.filmotheque.bll.IFilmService;
import fr.eni.filmotheque.bll.IParticipantService;
import fr.eni.filmotheque.bo.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RealisateurConverter implements Converter<String, Participant> {
    @Autowired
    private IParticipantService participantService;

    @Override
    public Participant convert(String participantTexte) {
        Participant participant = new Participant();

        try {
            participant = participantService.consulterParticipantParId(Long.parseLong(participantTexte));
        } catch (Exception e) {
            System.out.println("Erreur durant la conversion d\'un participant : " + e.getMessage());
        }

        return participant;
    }
}
