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
    private Boolean isUpdate = false;

    @GetMapping("/films")
    public String getFilms(Model model) {
        listeFilms = filmService.consulterFilms();
        model.addAttribute("films", listeFilms);

        return "films";
    }

    @GetMapping("/films/{id}")
    public String getFilms(@PathVariable("id") long id, Model model) {
        listeFilms = filmService.consulterFilms();
        for (Film film: listeFilms) {
            if (film.getId() == id) {
                model.addAttribute("film", film);
            }
        }

        model.addAttribute("isUpdate", isUpdate);

        return "filmDetails";
    }
}
