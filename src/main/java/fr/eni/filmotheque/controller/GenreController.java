package fr.eni.filmotheque.controller;

import fr.eni.filmotheque.bll.jpa.GenreServiceJpaImpl;
import fr.eni.filmotheque.bo.Genre;
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
public class GenreController {
    @Autowired
    private GenreServiceJpaImpl genreServiceJpa;

    @GetMapping("/genres")
    public String getGenres(Model model) {
        model.addAttribute("genre", new Genre());
        model.addAttribute("genres", genreServiceJpa.consulterGenres());

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
            System.out.println(bindingResult);
            model.addAttribute("genre", new Genre());
            return "genreCreation";
        }

        genreServiceJpa.creerGenre(genre);

        return "redirect:/genres";
    }

    @GetMapping("/genreDelete/{id}")
    public String getDeleteGenre(@PathVariable("id") long id) {
        genreServiceJpa.deleteGenre(id);

        return "redirect:/genres";
    }

    @GetMapping("/genreUpdate/{id}")
    public String getUpdateGenre(@PathVariable("id") long id, Model model) {
        model.addAttribute("genre", genreServiceJpa.consulterGenreParId(id));

        return "genreUpdate";
    }

    @PostMapping("/genreUpdate/{id}")
    public String postUpdateGenre(@PathVariable("id") long id, @Valid Genre genre, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("genre", genreServiceJpa.consulterGenreParId(id));
            return "genreUpdate";
        }

        genreServiceJpa.creerGenre(genre);

        return "redirect:/genres";
    }
}
