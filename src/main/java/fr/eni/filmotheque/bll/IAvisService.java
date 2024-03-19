package fr.eni.filmotheque.bll;

import fr.eni.filmotheque.bo.Avis;

import java.util.List;

public interface IAvisService {
    List<Avis> consulterAvis(long idFilm);

    Avis creerAvis(Avis avis);
}
