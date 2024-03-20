package fr.eni.filmotheque.security;

import fr.eni.filmotheque.bll.IMembreService;
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
    private IMembreService membreService;

    public MyUserDetailsService(IMembreService membreService) {
        this.membreService = membreService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Membre membre = membreService.consulterMembreParPseudo(username);

        if (membre != null) {
            return new Utilisateur(membre);
        }

        throw new UsernameNotFoundException(username);
    }
}
