package fr.eni.filmotheque.controller;

import fr.eni.filmotheque.bll.mock.FilmServiceBouchon;
import org.springframework.stereotype.Controller;

@Controller
public class FilmController {

    private FilmServiceBouchon filmService = new FilmServiceBouchon();

    public void afficherUnFilm(Long i) {
        System.out.println(filmService.consulterFilmParId(i));
    }

    public void afficherFilms() {
        System.out.println(filmService.consulterFilms());
    }
}
