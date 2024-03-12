package fr.eni.filmotheque.bo;

public class Participant extends Personne {

    public Participant(Long Id, String Nom, String Prenom) {
        super(Id, Nom, Prenom);
    }

    public Participant(String Nom, String Prenom) {
        super(Nom, Prenom);
    }

    public void add(Participant participant) {
    }
}
