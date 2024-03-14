package fr.eni.filmotheque.bo;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class Personne {
    private Long Id;
    @NotBlank(message = "Le nom ne peut pas être vide")
    public String Nom;
    @NotBlank(message = "Le prénom ne peut pas être vide")
    public String Prenom;

    public Personne(String nom, String prenom) {
        this.Nom = nom;
        this.Prenom = prenom;
    }
}
