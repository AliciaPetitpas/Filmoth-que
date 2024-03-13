package fr.eni.filmotheque.controller;

import fr.eni.filmotheque.bll.IFilmService;
import fr.eni.filmotheque.bo.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        model.addAttribute("isReadOnly", isReadOnly);

        return "filmDetails";
    }

    @GetMapping("/filmsUpdate/{id}")
    public String getFilmUpdate(@PathVariable("id") long id, Model model) {
//        isReadOnly = false;

        model.addAttribute("film", filmService.consulterFilmParId(id));
        model.addAttribute("isReadOnly", isReadOnly);

        return "filmUpdate";
    }
}
