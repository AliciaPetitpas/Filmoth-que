package fr.eni.filmotheque.bll.mock;

import fr.eni.filmotheque.bll.IMembreService;
import fr.eni.filmotheque.bo.Membre;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile("dev")
public class MembreServiceBouchon implements IMembreService {
    private List<Membre> listeMembres = new ArrayList<>();

    // @Autowired : pas besoin car PasswordEncoder est injecté dans le constructeur
    private PasswordEncoder passwordEncoder;

    /*
    Dans le constructeur
    * On initialise la liste des membres
    on injecte PasswordEncoder passwordEncoder
     */
    public MembreServiceBouchon(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        enregistrerMembre(new Membre(1L, "Cyril", "cyril", "admin", "Pa$$w0rd", true));
        enregistrerMembre(new Membre(2L, "Aurélie", "membre", "membre", "Pa$$w0rd", false));
    }

    // on gère un compteur pour l'id (qui simule l'auto-increment de la base de donnée)
    private long idCourant = 1;

    @Override
    public List<Membre> consulterMembres() {
        return listeMembres;
    }

    @Override
    public Membre consulterMembreParId(long id) {
        // on recherche dans la liste le membre d'id "id"
        for (Membre membre : listeMembres) {
            if (membre.getId() == id){
                return membre;
            }
        }
        // on retourne null si pas trouvé
        return null;
    }

    @Override
    public Membre consulterMembreParPseudo(String pseudo) {
        // je vais chercher le membre qui correspond au pseudo
        for (Membre membre : listeMembres) {
            if (membre.getPseudo().equals(pseudo)) {
                // lorsque je trouve le membre, je le renvoie
                return membre;
            }
        }
        // si le membre n'est pas trouvé, je renvoie null
        return null;
    }

    @Override
    public void supprimerMembreParId(long id) {
        // on recherche dans la liste l'index correspondant au membre d'id "id"
        for (int index = 0; index < listeMembres.size(); index++) {
            if (listeMembres.get(index).getId() == id){
                listeMembres.remove(index);
            }
        }
    }

    @Override
    public void modifierMembre(Membre membre) {
        Membre membreAModifier = consulterMembreParId(membre.getId());
        membreAModifier.setAdmin(membre.isAdmin());
        membreAModifier.setPseudo(membre.getPseudo());
        membreAModifier.setMotDePasse(membre.getMotDePasse());
    }

    @Override
    public void enregistrerMembre(Membre membre) {
        // on rajoute l'id au membre (tout en l'incrémentant)
        membre.setId(idCourant++);

        // NE PAS OUBLIER de mettre à jour le mot de passe en l'encodant
        membre.setMotDePasse(passwordEncoder.encode(membre.getMotDePasse()));

        // on rajoute le membre à la liste
        listeMembres.add(membre);
    }
}
