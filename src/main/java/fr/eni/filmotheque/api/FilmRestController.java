package fr.eni.filmotheque.api;

import fr.eni.filmotheque.bll.IFilmService;
import fr.eni.filmotheque.bll.IGenreService;
import fr.eni.filmotheque.bll.IMembreService;
import fr.eni.filmotheque.bll.IParticipantService;
import fr.eni.filmotheque.bo.Avis;
import fr.eni.filmotheque.bo.Film;
import fr.eni.filmotheque.bo.Membre;
import fr.eni.filmotheque.dto.SearchParams;
import fr.eni.filmotheque.security.Utilisateur;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/films")
public class FilmRestController {
    @Autowired
    private IFilmService filmService;
    @Autowired
    private IMembreService membreService;

    @GetMapping
    public List<Film> getFilms(SearchParams searchParams) {
        if (searchParams.getSearch() != null) {
            return filmService.recherche(searchParams);
        } else {
            return filmService.consulterFilms();
        }
    }

    @GetMapping("/{id}")
    public Film getFilm(@PathVariable("id") long id) {
        return filmService.consulterFilmParId(id);
    }

    @PostMapping
    public void postFilm(@RequestBody @Valid Film film) {
        filmService.creerFilm(film);
    }

    @PutMapping("/{id}")
    public void putFilm(@PathVariable("id") long id, @RequestBody @Valid Film film) {
        film.setId(id);

        filmService.creerFilm(film);
    }

    @DeleteMapping("/{id}")
    public void deleteFilm(@PathVariable("id") long id) {
        filmService.supprimerFilm(id);
    }

    @PostMapping("/{id}/avis")
    public void postAvis(@PathVariable("id") long id, @RequestBody @Valid Avis avis, @AuthenticationPrincipal Utilisateur utilisateurConnecte) {
        Membre membre = membreService.consulterMembreParId(1);
        avis.setMembre(membre);
//        avis.setMembre(utilisateurConnecte.getMembre());

        filmService.publierAvis(avis, id);
    }}
