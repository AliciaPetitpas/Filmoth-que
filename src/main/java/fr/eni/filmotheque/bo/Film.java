package fr.eni.filmotheque.bo;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data /* @Getter @Setter @ToString @Equals */
public class Film {
    public Long Id;
    public String Titre;
    public Integer Annee;
    public Integer Duree;
    public String Synopsis;

    @Setter
    public Genre genre;
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
