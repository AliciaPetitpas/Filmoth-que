package fr.eni.filmotheque.security;

import fr.eni.filmotheque.bo.Membre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {
    List<Membre> membres = new ArrayList<>();

    @Autowired
    private PasswordEncoder passwordEncoder;

    public MyUserDetailsService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;

        Membre membre1 = new Membre(1L, "Baille", "Anne-Lise", "abaille@campus-eni.fr", passwordEncoder.encode("membre1"));
        Membre membre2 = new Membre(2L, "Dupond", "Jean", "jean-dupond@campus-eni.fr", passwordEncoder.encode("membre2"));
        membre2.setAdmin(true);
        membres.add(membre1);
        membres.add(membre2);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        for (Membre membre : membres) {
            if (membre.getPseudo().equals(username)) {
                return new Utilisateur(membre);
            }
        }
        throw new UsernameNotFoundException(username);
    }
}
