package fr.eni.filmotheque.bll.jpa;

import fr.eni.filmotheque.bll.IMembreService;
import fr.eni.filmotheque.bo.Membre;
import fr.eni.filmotheque.dal.MembreJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Profile("prod")
public class MembreServiceJpaImpl implements IMembreService {
    private MembreJpaRepository membreJpaRepository;
    private PasswordEncoder passwordEncoder;

    public MembreServiceJpaImpl(PasswordEncoder passwordEncoder, MembreJpaRepository membreJpaRepository) throws Exception {
        this.passwordEncoder = passwordEncoder;
        this.membreJpaRepository = membreJpaRepository;

        if (membreJpaRepository.findAll().size() == 0) {
            Membre admin = new Membre(1L, "Dupond", "Jean", "jean-dupond@campus-eni.fr", "membre2", true);
            enregistrerMembre(admin);
        }
    }

    @Override
    public List<Membre> consulterMembres() {
        return membreJpaRepository.findAll();
    }

    @Override
    public Membre consulterMembreParId(long id) {
        return membreJpaRepository.findById(id).orElse(null);
    }

    @Override
    public Membre consulterMembreParPseudo(String pseudo) {
        for (Membre membre : consulterMembres()) {
            if (membre.getPseudo().equals(pseudo)) {
                return membre;
            }
        }
        return null;
    }

    @Override
    public void enregistrerMembre(Membre membre) throws Exception {
        if (consulterMembreParPseudo(membre.getPseudo()) != null) {
            throw new Exception("Erreur : le pseudo est déjà utilisé");
        }

        membre.setMotDePasse(passwordEncoder.encode(membre.getMotDePasse()));
        membreJpaRepository.save(membre);
    }

    @Override
    public void supprimerMembreParId(long id) {
        membreJpaRepository.deleteById(id);
    }

    @Override
    public void modifierMembre(Membre membre) {
        Membre membreAmodifier = consulterMembreParId(membre.getId());
        membreAmodifier.setAdmin(membre.isAdmin());
        membreAmodifier.setPseudo(membre.getPseudo());
        membreAmodifier.setMotDePasse(passwordEncoder.encode(membre.getMotDePasse()));
        membreJpaRepository.save(membreAmodifier);
    }
}
