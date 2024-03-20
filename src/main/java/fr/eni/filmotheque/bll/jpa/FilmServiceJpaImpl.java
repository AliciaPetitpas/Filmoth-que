package fr.eni.filmotheque.bll.jpa;

import fr.eni.filmotheque.bll.IFilmService;
import fr.eni.filmotheque.bo.Avis;
import fr.eni.filmotheque.bo.Film;
import fr.eni.filmotheque.dal.AvisJpaRepository;
import fr.eni.filmotheque.dal.FilmJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Profile("prod")
public class FilmServiceJpaImpl implements IFilmService {
    @Autowired
    private FilmJpaRepository filmJpaRepository;
    @Autowired
    private AvisJpaRepository avisJpaRepository;

    @Override
    public List<Film> consulterFilms() {
        return filmJpaRepository.findAll();
    }

    @Override
    public Film consulterFilmParId(long id) {
        return filmJpaRepository.findById(id).orElse(null);
    }

    @Override
    public void creerFilm(Film film) {
        filmJpaRepository.save(film);
    }

    @Override
    public void supprimerFilm(long id) {
        filmJpaRepository.deleteById(id);
    }

    @Override
    public void publierAvis(Avis avis, long id) {
        avis.setFilm(filmJpaRepository.findById(id).orElse(null));
        avisJpaRepository.save(avis);
    }
}
