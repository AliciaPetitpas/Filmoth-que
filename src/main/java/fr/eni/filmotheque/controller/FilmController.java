package fr.eni.filmotheque.controller;

import fr.eni.filmotheque.bll.IFilmService;
import fr.eni.filmotheque.bo.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class FilmController {

    @Autowired
    private IFilmService filmService;

    public void afficherUnFilm(Long i) {
        System.out.println(filmService.consulterFilmParId(i));
    }

    public void afficherFilms() {
        for (Film film: filmService.consulterFilms()) {
            System.out.println(film);
        }
    }
}
