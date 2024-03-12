package fr.eni.filmotheque.bo;

import lombok.*;

import java.util.ArrayList;

@Data /* @Getter @Setter @ToString @Equals */
public class Film {
    private Long Id;
    private String Titre;
    private Integer Annee;
    private Integer Duree;
    private String Synopsis;

    private Participant participant;
    private Avis avis;
    @Setter
    private Genre genre;

    ArrayList<Participant> listeParticipant = new ArrayList<>();
    ArrayList<Avis> listeAvis = new ArrayList<>();

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
        this.participant = realisateur;
    }

    public ArrayList<Participant> getActeurs() {
        return listeParticipant;
    }

    public ArrayList<Avis> getAvis() {
        return listeAvis;
    }
}
