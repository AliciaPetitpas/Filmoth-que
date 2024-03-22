package fr.eni.filmotheque.api;

import fr.eni.filmotheque.bll.IFilmService;
import fr.eni.filmotheque.bo.Film;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/films")
public class FilmRestController {
    @Autowired
    private IFilmService filmService;

    @GetMapping
    public List<Film> getFilms() {
        return filmService.consulterFilms();
    }

    @GetMapping("/{id}")
    public Film getFilm(@PathVariable("id") long id) {
        return filmService.consulterFilmParId(id);
    }

    @PostMapping
    public void postFilm(@RequestBody @Valid Film film) {
        filmService.creerFilm(film);
    }

    @PutMapping("/{id}")
    public void putFilm(@PathVariable("id") long id, @RequestBody @Valid Film film) {
        film.setId(id);

        filmService.creerFilm(film);
    }

    @DeleteMapping("/{id}")
    public void deleteFilm(@PathVariable("id") long id) {
        filmService.supprimerFilm(id);
    }
}
