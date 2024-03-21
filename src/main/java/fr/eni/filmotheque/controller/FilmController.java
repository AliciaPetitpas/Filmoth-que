package fr.eni.filmotheque.controller;

import fr.eni.filmotheque.bll.IFilmService;
import fr.eni.filmotheque.bll.IGenreService;
import fr.eni.filmotheque.bll.IMembreService;
import fr.eni.filmotheque.bll.IParticipantService;
import fr.eni.filmotheque.bo.Avis;
import fr.eni.filmotheque.bo.Film;
import fr.eni.filmotheque.bo.Genre;
import fr.eni.filmotheque.bo.Participant;
import fr.eni.filmotheque.dto.SearchParams;
import fr.eni.filmotheque.security.Utilisateur;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    private IMembreService membreService;

    @ModelAttribute("listeGenres")
    public  List<Genre> listeGenres(){
        return genreService.consulterGenres();
    }

    @ModelAttribute("listeParticipants")
    public  List<Participant> listeParticipants(){
        return participantService.consulterParticipants();
    }

    @GetMapping("/films")
    public String getFilms(SearchParams searchParams, Model model) {
        model.addAttribute("listeGenres", genreService.consulterGenres());

        if (searchParams.getSearch() != null) {
            model.addAttribute("films", filmService.recherche(searchParams));
        } else {
            model.addAttribute("films", filmService.consulterFilms());
        }

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
        model.addAttribute("id", id);

        return "filmUpdate";
    }

    @PostMapping("/filmUpdate/{id}")
    public String postFilmUpdate(@PathVariable("id") long id, @Valid Film film, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("listeGenres", genreService.consulterGenres());
            model.addAttribute("listeParticipants", participantService.consulterParticipants());
            model.addAttribute("film", filmService.consulterFilmParId(id));
            model.addAttribute("id", id);
            return "filmUpdate";
        }

        filmService.modifierFilm(film);

        return "redirect:/films";
    }

    @GetMapping("/avisCreation/{id}")
    public String getCreationAvis(@PathVariable("id") long id, Model model) {
        model.addAttribute("film", filmService.consulterFilmParId(id));
        model.addAttribute("avis", new Avis());

        return "avisCreation";
    }

    @PostMapping("/avisCreation/{id}")
    public String postCreationAvis(@PathVariable("id") long id, @Valid Avis avis, BindingResult bindingResult, Model model,@AuthenticationPrincipal Utilisateur utilisateurConnecte) {
        model.addAttribute("avis", new Avis());

        if (bindingResult.hasErrors()) {
            model.addAttribute("films", filmService.consulterFilms());

            return "films";
        }

        avis.setMembre(utilisateurConnecte.getMembre());

        filmService.publierAvis(avis, id);

        return "redirect:/films/" + id;
    }
}
