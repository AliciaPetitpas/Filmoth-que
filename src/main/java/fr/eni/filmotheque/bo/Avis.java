package fr.eni.filmotheque.bo;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Avis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @NotNull(message = "Veuillez saisir une note entre 1 et 5")
    @Min(1)
    @Max(5)
    public Integer Note;
    public String Commentaire;
    @ManyToOne
    public Membre Membre;

    @ManyToOne
    Film film;

    public void setFilm(Film film) {
        this.film = film;
        film.getAvis().add(this);
    }
}
