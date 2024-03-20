package fr.eni.filmotheque.bo;

import jakarta.persistence.Entity;

@Entity
public class Participant extends Personne {
    public Participant(Long Id, String Nom, String Prenom) {
        super(Id, Nom, Prenom);
    }

    public Participant(String Nom, String Prenom) {
        super(Nom, Prenom);
    }

    public Participant() {

    }


    @Override
    public String toString() {
        return this.getPrenom() + " " + this.getNom();
    }
}
