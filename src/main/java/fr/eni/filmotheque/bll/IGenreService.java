package fr.eni.filmotheque.bll;

import fr.eni.filmotheque.bo.Genre;

import java.util.List;

public interface IGenreService {

    List<Genre> consulterGenres();

    Genre consulterGenreParId(long id);

    Genre enregistrerGenre(Genre genre);

    void supprimerGenreParId(long id);

    void modifierGenre(Genre genre);
}
