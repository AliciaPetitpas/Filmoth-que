package fr.eni.filmotheque.controller;

import fr.eni.filmotheque.bll.IFilmService;
import fr.eni.filmotheque.bo.Film;
import fr.eni.filmotheque.bo.Genre;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GenreController {
    @Autowired
    private IFilmService filmService;

    List<Genre> listeGenres = new ArrayList<>();

    @GetMapping("/genres")
    public String getGenres(Model model) {
        model.addAttribute("genre", new Genre());
        listeGenres = filmService.consulterGenres();
        model.addAttribute("genres", listeGenres);

        return "genres";
    }

    @PostMapping("/genres")
    public String postGenres(@Valid Genre genre, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            listeGenres = filmService.consulterGenres();
            model.addAttribute("genres", listeGenres);
            return "genres";
        }

        genre.setId((long) (listeGenres.size() + 1));

        listeGenres.add(genre);

        return "redirect:/genres";
    }

    @GetMapping("/genreCreation")
    public String getCreationGenre(Model model) {
        model.addAttribute("genre", new Genre());

        return "genreCreation";
    }

    @PostMapping("/genreCreation")
    public String postCreationGenre(@Valid Genre genre, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("genre", new Genre());
            return "genreCreation";
        }
        listeGenres = filmService.consulterGenres();

        genre.setId((long) (listeGenres.size() + 1));
        listeGenres.add(genre);

        return "redirect:/genres";
    }

    @GetMapping("/genreUpdate/{id}")
    public String getUpdateGenre(@PathVariable("id") long id, Model model) {
        model.addAttribute("genre", filmService.consulterGenreParId(id));

        return "genreCreation";
    }

    @PostMapping("/genreUpdate/{id}")
    public String postUpdateGenre(@PathVariable("id") long id, @Valid Genre genre, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("genre", filmService.consulterGenreParId(id));
            return "genreCreation";
        }

        Genre genreAUpdate = filmService.consulterGenreParId(id);
        genreAUpdate.setTitre(genre.Titre);

        listeGenres = filmService.consulterGenres();

        return "redirect:/genres";
    }
}
