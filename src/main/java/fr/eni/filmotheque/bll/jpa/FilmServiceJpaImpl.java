package fr.eni.filmotheque.bll.jpa;

import fr.eni.filmotheque.bll.IFilmService;
import fr.eni.filmotheque.bo.Avis;
import fr.eni.filmotheque.bo.Film;
import fr.eni.filmotheque.dal.AvisJpaRepository;
import fr.eni.filmotheque.dal.FilmJpaRepository;
import fr.eni.filmotheque.dto.SearchParams;
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
    public void modifierFilm(Film film) {
        Film filmAModifier = consulterFilmParId(film.Id);
        filmAModifier.setTitre(film.getTitre());
        filmAModifier.setGenre(film.getGenre());
        filmAModifier.setAnnee(film.getAnnee());
        filmAModifier.setRealisateur(film.getRealisateur());
        filmAModifier.setDuree(film.getDuree());
        filmAModifier.setListeParticipant(film.getListeParticipant());
        filmAModifier.setSynopsis(film.getSynopsis());
        filmJpaRepository.save(filmAModifier);
    }

    @Override
    public void publierAvis(Avis avis, long id) {
        avis.setFilm(filmJpaRepository.findById(id).orElse(null));
        avisJpaRepository.save(avis);
    }

    @Override
    public List<Film> consulterFilmsParTitre(String titre) {
        return filmJpaRepository.findByTitre(titre);
    }

    @Override
    public List<Film> consulterFilmsParTitreLike(String titreStart, String titreEnd) {
        return filmJpaRepository.findByTitreStartingWithOrTitreEndingWith(titreStart, titreEnd);
    }

    @Override
    public List<Film> recherche(SearchParams searchParams) {
        return filmJpaRepository.recherche(searchParams);
    }

    public void publierAvisUnidirectionnel(Avis avis, long id) {
        Film film = consulterFilmParId(id);
        film.getAvis().add(avis);

        filmJpaRepository.save(film);
    }
}
