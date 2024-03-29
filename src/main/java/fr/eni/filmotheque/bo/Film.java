package fr.eni.filmotheque.bo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data /* @Getter @Setter @ToString @Equals */
@NoArgsConstructor
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long Id;
    @NotBlank(message = "Veuillez renseigner un titre au film")
    public String titre;
    public Integer Annee;
    public Integer Duree;
    public String Synopsis;

    @Setter
    @ManyToOne
    public Genre genre;

    @OneToMany(cascade = CascadeType.MERGE)
    @JoinColumn(name = "film_id")
    public List<Avis> listeAvis = new ArrayList<>();

    @Setter
    @ManyToOne
    public Participant realisateur;

    @ManyToMany
    public List<Participant> listeParticipant = new ArrayList<>();

    public Film(Long id, String titre, Integer annee, Integer duree, String synopsis) {
        this.Id = id;
        this.titre = titre;
        this.Annee = annee;
        this.Duree = duree;
        this.Synopsis = synopsis;
    }

    public Film(String titre, Integer annee, Integer duree, String synopsis) {
        this.titre = titre;
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
