package fr.eni.filmotheque.bo;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Avis {
    private Long Id;
    private Integer Note;
    private String Commentaire;
    private Membre Membre;

    public Avis(Integer note, String commentaire, Membre membre) {
        this.Note = note;
        this.Commentaire = commentaire;
        this.Membre = membre;
    }

    public void add(Avis avis) {
        Avis newAvis = new Avis(avis.Id, avis.Note, avis.Commentaire, avis.Membre);
    }
}
