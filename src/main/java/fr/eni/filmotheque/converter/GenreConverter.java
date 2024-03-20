package fr.eni.filmotheque.converter;

import fr.eni.filmotheque.bll.jpa.GenreServiceJpaImpl;
import fr.eni.filmotheque.bo.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class GenreConverter implements Converter<String, Genre> {
    @Autowired
    private GenreServiceJpaImpl genreServiceJpa;

    @Override
    public Genre convert(String participantTexte) {
        Genre genre = new Genre();

        try {
            genre = genreServiceJpa.consulterGenreParId(Long.parseLong(participantTexte));
        } catch (Exception e) {
            System.out.println("Erreur durant la conversion d\'un genre : " + e.getMessage());
        }

        return genre;
    }
}
