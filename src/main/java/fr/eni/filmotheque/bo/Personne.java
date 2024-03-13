package fr.eni.filmotheque.bo;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public abstract class Personne {
    private Long Id;
    public String Nom;
    public String Prenom;

    public Personne(String nom, String prenom) {
        this.Nom = nom;
        this.Prenom = prenom;
    }
}
