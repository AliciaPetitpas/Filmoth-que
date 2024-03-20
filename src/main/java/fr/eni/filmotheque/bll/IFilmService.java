package fr.eni.filmotheque.bll;

import fr.eni.filmotheque.bo.*;

import java.util.List;
import java.util.Optional;

public interface IFilmService {
    List<Film> consulterFilms();

    Film consulterFilmParId(long id);

    void creerFilm(Film film);

    void supprimerFilm(long id);

    void publierAvis(Avis avis, long id);
}
