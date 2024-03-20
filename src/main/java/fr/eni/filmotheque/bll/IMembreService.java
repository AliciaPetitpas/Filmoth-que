package fr.eni.filmotheque.bll;

import fr.eni.filmotheque.bo.Membre;

import java.util.List;
import java.util.Optional;

public interface IMembreService {
    List<Membre> consulterMembres();

    Membre consulterMembreParId(long id);

    Membre consulterMembreParPseudo(String pseudo);

    void enregistrerMembre(Membre membre) throws Exception;

    void supprimerMembreParId(long id);
}
