package fr.eni.filmotheque.bll;

import fr.eni.filmotheque.bo.*;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IFilmService {
    List<Film> consulterFilms();

    Film consulterFilmParId(long id);

    List<Participant> consulterParticipants();

    List<Membre> consulterMembres();

    Participant consulterParticipantParId(long id);

    List<Avis> consulterAvis(long idFilm);

    void creerFilm(Film film);

    Membre consulterMembreParId(long id);
}
