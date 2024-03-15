package fr.eni.filmotheque.controller;

import fr.eni.filmotheque.bll.IFilmService;
import fr.eni.filmotheque.bo.Avis;
import fr.eni.filmotheque.bo.Film;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FilmController {

    @Autowired
    private IFilmService filmService;

    private List<Film> listeFilms = new ArrayList<>();
    List<Avis> listeAvis = new ArrayList<>();

    @GetMapping("/films")
    public String getFilms(Model model) {
        listeFilms = filmService.consulterFilms();
        model.addAttribute("films", listeFilms);

        return "films";
    }

    @GetMapping("/films/{id}")
    public String getFilmDetail(@PathVariable("id") long id, Model model) {
        model.addAttribute("film", filmService.consulterFilmParId(id));
        model.addAttribute("isReadOnly", true);

        return "filmDetails";
    }

    @GetMapping("/filmCreation")
    public String getCreationFilm(Model model) {
        model.addAttribute("film", new Film());
        model.addAttribute("listeGenres", filmService.consulterGenres());
        model.addAttribute("listeParticipants", filmService.consulterParticipants());

        return "filmCreation";
    }

    @PostMapping("/filmCreation")
    public String postCreationFilm(@Valid Film film, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("listeGenres", filmService.consulterGenres());
            model.addAttribute("listeParticipants", filmService.consulterParticipants());
            return "filmCreation";
        }

        listeFilms = filmService.consulterFilms();

        film.setId((long) (listeFilms.size() + 1));
        listeFilms.add(film);

        return "redirect:/films";
    }

    @GetMapping("/filmUpdate/{id}")
    public String getFilmUpdate(@PathVariable("id") long id, Model model) {
        model.addAttribute("listeGenres", filmService.consulterGenres());
        model.addAttribute("listeParticipants", filmService.consulterParticipants());
        model.addAttribute("film", filmService.consulterFilmParId(id));
        model.addAttribute("isReadOnly", false);

        return "filmUpdate";
    }

    @PostMapping("/filmUpdate/{id}")
    public String postFilmUpdate(@PathVariable("id") long id, @Valid Film film, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("listeGenres", filmService.consulterGenres());
            model.addAttribute("listeParticipants", filmService.consulterParticipants());
            model.addAttribute("film", filmService.consulterFilmParId(id));
            return "filmUpdate";
        }

        Film filmAUpdate = filmService.consulterFilmParId(id);
        filmAUpdate.setTitre(film.Titre);
        filmAUpdate.setGenre(film.genre);
        filmAUpdate.setAnnee(film.Annee);
        filmAUpdate.setRealisateur(film.realisateur);
        filmAUpdate.setDuree(film.Duree);
        filmAUpdate.setListeParticipant(film.listeParticipant);
        filmAUpdate.setSynopsis(film.Synopsis);

        listeFilms = filmService.consulterFilms();

        return "redirect:/films";
    }
}
