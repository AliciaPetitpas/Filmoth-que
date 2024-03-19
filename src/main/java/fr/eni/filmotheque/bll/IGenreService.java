package fr.eni.filmotheque.bll;

import fr.eni.filmotheque.bo.Genre;

import java.util.List;
import java.util.Optional;

public interface IGenreService {

    List<Genre> consulterGenres();

    Optional<Genre> consulterGenreParId(long id);

    Genre creerGenre(Genre genre);

    void deleteGenre(Long id);
}
