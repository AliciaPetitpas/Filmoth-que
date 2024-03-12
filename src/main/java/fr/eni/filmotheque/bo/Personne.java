package fr.eni.filmotheque.bo;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public abstract class Personne {
    private Long Id;
    private String Nom;
    private String Prenom;

    public Personne(String nom, String prenom) {
        this.Nom = nom;
        this.Prenom = prenom;
    }
}
