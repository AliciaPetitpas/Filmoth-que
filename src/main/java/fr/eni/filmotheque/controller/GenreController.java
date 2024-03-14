package fr.eni.filmotheque.controller;

import fr.eni.filmotheque.bll.IFilmService;
import fr.eni.filmotheque.bo.Genre;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/genres")
public class GenreController {
    @Autowired
    private IFilmService filmService;

    List<Genre> listeGenres = new ArrayList<>();

    @GetMapping
    public String getGenres(Model model) {
        model.addAttribute("genre", new Genre());
        listeGenres = filmService.consulterGenres();
        model.addAttribute("genres", listeGenres);

        return "genres";
    }

    @PostMapping
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
}
