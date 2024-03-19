package fr.eni.filmotheque.bo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class Personne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
