package fr.eni.filmotheque.bo;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Avis {
    private Long Id;
    @NotNull(message = "Veuillez saisir une note entre 1 et 5")
    @Min(1)
    @Max(5)
    public Integer Note;
    public String Commentaire;
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
