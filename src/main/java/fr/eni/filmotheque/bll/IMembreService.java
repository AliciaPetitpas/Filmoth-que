package fr.eni.filmotheque.bll;

import fr.eni.filmotheque.bo.Membre;

import java.util.List;
import java.util.Optional;

public interface IMembreService {
    List<Membre> consulterMembres();

    Optional<Membre> consulterMembreParId(long id);
}
