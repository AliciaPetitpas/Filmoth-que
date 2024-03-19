package fr.eni.filmotheque.bll.jpa;

import fr.eni.filmotheque.bll.IFilmService;
import fr.eni.filmotheque.bo.Film;
import fr.eni.filmotheque.dal.FilmJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Profile("prod")
@Service
public class FilmServiceJpaImpl implements IFilmService {
    @Autowired
    private FilmJpaRepository filmJpaRepository;

    @Override
    public List<Film> consulterFilms() {
        return filmJpaRepository.findAll();
    }

    @Override
    public Optional<Film> consulterFilmParId(long id) {
        return filmJpaRepository.findById(id);
    }

    @Override
    public void creerFilm(Film film) {
        filmJpaRepository.save(film);
    }
}
