package fr.eni.filmotheque.converter;

import fr.eni.filmotheque.bll.IFilmService;
import fr.eni.filmotheque.bo.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RealisateurConverter implements Converter<String, Participant> {
    @Autowired
    private IFilmService filmService;

    @Override
    public Participant convert(String participantTexte) {
        Participant participant = new Participant();

        try {
            participant = filmService.consulterParticipantParId(Long.parseLong(participantTexte));
        } catch (Exception e) {
            System.out.println("Erreur durant la conversion d\'un participant : " + e.getMessage());
        }

        return participant;
    }
}
