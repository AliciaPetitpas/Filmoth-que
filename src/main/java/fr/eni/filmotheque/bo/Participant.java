package fr.eni.filmotheque.bo;

public class Participant extends Personne {

    public Participant(Long Id, String Nom, String Prenom) {
        super(Id, Nom, Prenom);
    }

    public Participant() {
    }

    @Override
    public String toString() {
        return this.getPrenom() + " " + this.getNom();
    }
}