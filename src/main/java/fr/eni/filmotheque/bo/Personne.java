package fr.eni.filmotheque.bo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@MappedSuperclass // Ne crée pas de table pour PERSONNE
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Personne {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

