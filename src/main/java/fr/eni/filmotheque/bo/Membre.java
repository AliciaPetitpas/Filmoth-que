package fr.eni.filmotheque.bo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Membre extends Personne {
    private String Pseudo;
    private String motDePasse;
    private boolean isAdmin;

    public Membre(Long Id, String Nom, String Prenom, String pseudo, String mdp) {
        super(Id, Nom, Prenom);
        this.Pseudo = pseudo;
        this.motDePasse = mdp;
    }

    public Membre(String Nom, String Prenom, String pseudo, String mdp) {
        super(Nom, Prenom);
        this.Pseudo = pseudo;
        this.motDePasse = mdp;
    }
}
