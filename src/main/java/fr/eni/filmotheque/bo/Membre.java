package fr.eni.filmotheque.bo;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Membre extends Personne {
    @NotBlank(message = "Le pseudo ne peut pas être vide")
    public String Pseudo;
    private String motDePasse;
    public boolean isAdmin = false;

    public Membre(Long Id, String Nom, String Prenom, String pseudo, String mdp) {
        super(Id, Nom, Prenom);
        this.Pseudo = pseudo;
        this.motDePasse = mdp;
    }

    public Membre() {
    }
}
