package fr.eni.filmotheque.bo;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Genre {
    private Long Id;
    private String Titre;

    public Genre(String titre) {
        this.Titre = titre;
    }
}
