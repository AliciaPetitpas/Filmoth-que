package fr.eni.filmotheque.bll;

import fr.eni.filmotheque.bo.Film;
import fr.eni.filmotheque.bo.Genre;
import fr.eni.filmotheque.bo.Participant;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IFilmService {
    List<Film> consulterFilms();

    Film consulterFilmParId(long id);

    List<Genre> consulterGenres();

    List<Participant> consulterParticipants();

    Genre consulterGenreParId(long id);

    Participant consulterParticipantParId(long id);

    void creerFilm(Film film);
}
