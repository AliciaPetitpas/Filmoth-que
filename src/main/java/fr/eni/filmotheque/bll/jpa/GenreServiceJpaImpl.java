package fr.eni.filmotheque.bll.jpa;

import fr.eni.filmotheque.bll.IGenreService;
import fr.eni.filmotheque.bo.*;
import fr.eni.filmotheque.dal.GenreJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Profile("prod")
public class GenreServiceJpaImpl implements IGenreService {

    @Autowired
    private GenreJpaRepository genreJpaRepository;

    @Override
    public List<Genre> consulterGenres() {
        return genreJpaRepository.findAll();
    }

    @Override
    public Genre consulterGenreParId(long id) {
        return genreJpaRepository.findById(id).orElse(null);
    }


    @Override
    public Genre enregistrerGenre(Genre genre) {
        return genreJpaRepository.save(genre);
    }

    @Override
    public void supprimerGenreParId(long id) {
        genreJpaRepository.deleteById(id);
    }

    @Override
    public void updateGenre(Genre genre) {
        List<Genre> listeGenres = genreJpaRepository.findAll();
        for (Genre genreAUpdate : listeGenres) {
            if (Objects.equals(genreAUpdate.getId(), genre.getId())) {
                genreAUpdate.setTitre(genre.Titre);
            }
        }
    }
}
