package fr.eni.filmotheque.bll;

import fr.eni.filmotheque.bo.*;

import java.util.List;

public interface IFilmServiceMock {
    List<Film> consulterFilms();

    Film consulterFilmParId(long id);

    List<Genre> consulterGenres();

    List<Participant> consulterParticipants();

    List<Membre> consulterMembres();

    Genre consulterGenreParId(long id);

    Participant consulterParticipantParId(long id);

    List<Avis> consulterAvis(long idFilm);

    void creerFilm(Film film);

    Membre consulterMembreParId(long id);
}
