package fr.eni.filmotheque.bo;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Genre {
    private Long Id;
    @NotBlank(message = "Le nom du genre ne peut pas être vide")
    public String Titre;

    public Genre(String titre) {
        this.Titre = titre;
    }
}
