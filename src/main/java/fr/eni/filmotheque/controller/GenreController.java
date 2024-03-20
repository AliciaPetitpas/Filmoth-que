package fr.eni.filmotheque.controller;

import fr.eni.filmotheque.bll.IGenreService;
import fr.eni.filmotheque.bo.Genre;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GenreController {
    @Autowired
    private IGenreService genreService;

    @GetMapping("/genres")
    public String getGenres(Model model) {
        model.addAttribute("genre", new Genre());
        model.addAttribute("genres", genreService.consulterGenres());

        return "genres";
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

        genreService.enregistrerGenre(genre);

        return "redirect:/genres";
    }

    @GetMapping("/genreDelete/{id}")
    public String getDeleteGenre(@PathVariable("id") long id) {
        genreService.supprimerGenreParId(id);

        return "redirect:/genres";
    }

    @GetMapping("/genreUpdate/{id}")
    public String getUpdateGenre(@PathVariable("id") long id, Model model) {
        model.addAttribute("genre", genreService.consulterGenreParId(id));
        model.addAttribute("id", id);

        return "genreUpdate";
    }

    @PostMapping("/genreUpdate/{id}")
    public String postUpdateGenre(@PathVariable("id") long id, @Valid Genre genre, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("genre", genreService.consulterGenreParId(id));
            model.addAttribute("id", id);
            return "genreUpdate";
        }

        // TODO update genre

        return "redirect:/genres";
    }
}
