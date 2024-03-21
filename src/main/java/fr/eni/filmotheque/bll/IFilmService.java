package fr.eni.filmotheque.bll;

import fr.eni.filmotheque.bo.*;
import fr.eni.filmotheque.dto.SearchParams;

import java.util.List;
import java.util.Optional;

public interface IFilmService {
    List<Film> consulterFilms();

    Film consulterFilmParId(long id);

    void creerFilm(Film film);

    void supprimerFilm(long id);

    void modifierFilm(Film film);

    void publierAvis(Avis avis, long id);

    List<Film> consulterFilmsParTitre(String titre);

    List<Film> consulterFilmsParTitreLike(String titreStart, String titreEnd);

    List<Film> recherche(SearchParams searchParams);
}
