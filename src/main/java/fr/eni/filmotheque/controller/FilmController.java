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
    private Boolean isReadOnly = true;

    @GetMapping("/films")
    public String getFilms(Model model) {
        listeFilms = filmService.consulterFilms();
        model.addAttribute("films", listeFilms);

        return "films";
    }

    @GetMapping("/films/{id}")
    public String getFilmDetail(@PathVariable("id") long id, Model model) {
        model.addAttribute("film", filmService.consulterFilmParId(id));
        model.addAttribute("avis", new Avis());
        model.addAttribute("isReadOnly", isReadOnly);

        return "filmDetails";
    }

    @PostMapping("/films/{id}")
    public String postFilmDetail(@PathVariable("id") long id, @Valid Avis avis, BindingResult bindingResult, Model model) {
        model.addAttribute("avis", new Avis());
        model.addAttribute("isReadOnly", isReadOnly);

        if (bindingResult.hasErrors()) {
            model.addAttribute("film", filmService.consulterFilmParId(id));

            return "/films";
        }

        listeAvis = filmService.consulterAvis(id);
        avis.setId((long) (listeAvis.size() + 1));

        System.out.println(listeAvis);

        return "redirect:/filmDetails";
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
            System.out.println(bindingResult);
            model.addAttribute("listeGenres", filmService.consulterGenres());
            model.addAttribute("listeParticipants", filmService.consulterParticipants());
            return "filmCreation";
        }

        listeFilms = filmService.consulterFilms();

        film.setId((long) (listeFilms.size() + 1));
        listeFilms.add(film);

        return "redirect:/films";
    }

}
