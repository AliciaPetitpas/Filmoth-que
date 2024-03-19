package fr.eni.filmotheque.bll.jpa;

import fr.eni.filmotheque.bll.IGenreService;
import fr.eni.filmotheque.bo.*;
import fr.eni.filmotheque.dal.GenreJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Profile("prod")
@Service
public class GenreServiceJpaImpl implements IGenreService {

    @Autowired
    private GenreJpaRepository genreJpaRepository;

    @Override
    public List<Genre> consulterGenres() {
        return genreJpaRepository.findAll();
    }

    @Override
    public Optional<Genre> consulterGenreParId(long id) {
        return genreJpaRepository.findById(id);
    }

    @Override
    public Genre creerGenre(Genre genre) {
        return genreJpaRepository.save(genre);
    }

    @Override
    public void deleteGenre(Long id) {
        genreJpaRepository.deleteById(id);
    }
}
