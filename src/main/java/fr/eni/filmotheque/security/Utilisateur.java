package fr.eni.filmotheque.security;

import fr.eni.filmotheque.bo.Membre;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
public class Utilisateur implements UserDetails {
    private Membre membre;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (membre.isAdmin()) {
            return List.of(new SimpleGrantedAuthority("ROLE_admin"));
        }
        return List.of(new SimpleGrantedAuthority("ROLE_user"));
    }

    @Override
    public String getPassword() {
        return membre.getMotDePasse();
    }

    @Override
    public String getUsername() {
        return membre.getPseudo();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Membre getMembre() {
        return membre;
    }
}
