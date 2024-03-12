package fr.eni.filmotheque.bo;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data /* @Getter @Setter @ToString @Equals */
public class Film {
    private Long Id;
    private String Titre;
    private Integer Annee;
    private Integer Duree;
    private String Synopsis;

    @Setter
    private Genre genre;
    private Participant realisateur;
    private List<Avis> listeAvis = new ArrayList<>();
    private List<Participant> listeParticipant = new ArrayList<>();

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

    public void setRealisateur(Participant realisateur) {
        this.realisateur = realisateur;
    }

    public List<Participant> getActeurs() {
        return listeParticipant;
    }

    public List<Avis> getAvis() {
        return listeAvis;
    }
}
