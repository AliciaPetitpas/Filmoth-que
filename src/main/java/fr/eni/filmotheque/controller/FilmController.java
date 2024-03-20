package fr.eni.filmotheque.controller;

import fr.eni.filmotheque.bll.IFilmService;
import fr.eni.filmotheque.bll.IGenreService;
import fr.eni.filmotheque.bll.IParticipantService;
import fr.eni.filmotheque.bo.Avis;
import fr.eni.filmotheque.bo.Film;
import fr.eni.filmotheque.bo.Genre;
import fr.eni.filmotheque.bo.Participant;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FilmController {

    @Autowired
    private IFilmService filmService;
    @Autowired
    private IParticipantService participantService;
    @Autowired
    private IGenreService genreService;

    @ModelAttribute("listeGenres")
    public  List<Genre> listeGenres(){
        return genreService.consulterGenres();
    }

    @ModelAttribute("listeParticipants")
    public  List<Participant> listeParticipants(){
        return participantService.consulterParticipants();
    }

    @GetMapping("/films")
    public String getFilms(Model model) {
        model.addAttribute("films", filmService.consulterFilms());

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
        model.addAttribute("listeGenres", genreService.consulterGenres());
        model.addAttribute("listeParticipants", participantService.consulterParticipants());

        return "filmCreation";
    }

    @PostMapping("/filmCreation")
    public String postCreationFilm(@Valid Film film, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("listeGenres", genreService.consulterGenres());
            model.addAttribute("listeParticipants", participantService.consulterParticipants());
            return "filmCreation";
        }

        filmService.creerFilm(film);

        return "redirect:/films";
    }

    @GetMapping("filmDelete/{id}")
    public String getFilmDelete(@PathVariable("id") long id) {
        filmService.supprimerFilm(id);

        return "redirect:/films";
    }

    @GetMapping("/filmUpdate/{id}")
    public String getFilmUpdate(@PathVariable("id") long id, Model model) {
        model.addAttribute("listeGenres", genreService.consulterGenres());
        model.addAttribute("listeParticipants", participantService.consulterParticipants());
        model.addAttribute("film", filmService.consulterFilmParId(id));
        model.addAttribute("isReadOnly", false);

        return "filmUpdate";
    }

    @PostMapping("/filmUpdate/{id}")
    public String postFilmUpdate(@PathVariable("id") long id, @Valid Film film, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("listeGenres", genreService.consulterGenres());
            model.addAttribute("listeParticipants", participantService.consulterParticipants());
            model.addAttribute("film", filmService.consulterFilmParId(id));
            return "filmUpdate";
        }

        // TODO update film

        return "redirect:/films";
    }
}
