package fr.eni.filmotheque.bll.mock;

import fr.eni.filmotheque.bll.IGenreService;
import fr.eni.filmotheque.bo.Genre;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile("dev")
public class GenreServiceBouchon implements IGenreService {
    private List<Genre> listeGenres = new ArrayList<>();

    public GenreServiceBouchon() {
        enregistrerGenre(new Genre("Animation"));
        enregistrerGenre(new Genre("Science-fiction"));
        enregistrerGenre(new Genre("Documentaire"));
        enregistrerGenre(new Genre("Action"));
        enregistrerGenre(new Genre("Drame"));
    }

    private long idCourant = 1;

    @Override
    public List<Genre> consulterGenres() {
        return listeGenres;
    }

    @Override
    public Genre consulterGenreParId(long id) {
        // on recherche dans la liste le genre d'id "id"
        for (Genre genre : listeGenres) {
            if (genre.getId() == id){
                return genre;
            }
        }
        // on retourne null si pas trouvé
        return null;
    }

    @Override
    public void supprimerGenreParId(long id) {
        // on recherche dans la liste l'index correspondant au genre d'id "id"
        for (int index = 0; index < listeGenres.size(); index++) {
            if (listeGenres.get(index).getId() == id){
                listeGenres.remove(index);
            }
        }
    }

    @Override
    public void modifierGenre(Genre genre) {
        Genre genreAModifier = consulterGenreParId(genre.getId());
        genreAModifier.setTitre(genre.getTitre());
    }

    @Override
    public Genre enregistrerGenre(Genre genre) {
        // on rajoute l'id au genre (tout en l'incrémentant)
        genre.setId(idCourant++);
        // on rajoute le genre à la liste
        listeGenres.add(genre);
        return genre;
    }
}
