package fr.eni.filmotheque.bo;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data /* @Getter @Setter @ToString @Equals */
@NoArgsConstructor
public class Film {
    public Long Id;
    @NotBlank(message = "Veuillez renseigner un titre au film")
    public String Titre;
    public Integer Annee;
    public Integer Duree;
    public String Synopsis;

    @Setter
    public Genre genre;
    @Setter
    public Participant realisateur;
    public List<Avis> listeAvis = new ArrayList<>();
    public List<Participant> listeParticipant = new ArrayList<>();

    public Film(Long id, String titre, Integer annee, Integer duree, String synopsis) {
        this.Id = id;
        this.Titre = titre;
        this.Annee = annee;
        this.Duree = duree;
        this.Synopsis = synopsis;
    }

    public Film(String titre, Integer annee, Integer duree, String synopsis) {
        this.Titre = titre;
        this.Annee = annee;
        this.Duree = duree;
        this.Synopsis = synopsis;
    }

    public List<Participant> getActeurs() {
        return listeParticipant;
    }

    public List<Avis> getAvis() {
        return listeAvis;
    }
}
