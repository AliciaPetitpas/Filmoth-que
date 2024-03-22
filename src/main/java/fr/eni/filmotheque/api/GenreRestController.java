package fr.eni.filmotheque.api;

import fr.eni.filmotheque.bll.IGenreService;
import fr.eni.filmotheque.bo.Genre;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/genres")
public class GenreRestController {
    @Autowired
    private IGenreService genreService;

    @GetMapping
    public List<Genre> getGenres() {
        return genreService.consulterGenres();
    }

    @GetMapping("/{id}")
    public Genre getGenre(@PathVariable("id") long id) {
        return genreService.consulterGenreParId(id);
    }

    @PostMapping
    public void postGenre(@RequestBody @Valid Genre genre) {
        genreService.enregistrerGenre(genre);
    }

    @PutMapping("/{id}")
    public void putGenre(@PathVariable("id") long id, @RequestBody @Valid Genre genre) {
        genre.setId(id);

        genreService.enregistrerGenre(genre);
    }

    @DeleteMapping("/{id}")
    public void deleteGenre(@PathVariable("id") long id) {
        genreService.supprimerGenreParId(id);
    }
}
