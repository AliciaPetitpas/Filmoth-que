package fr.eni.filmotheque.bll.jpa;

import fr.eni.filmotheque.bll.IMembreService;
import fr.eni.filmotheque.bo.Membre;
import fr.eni.filmotheque.dal.MembreJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Profile("prod")
@Service
public class MembreServiceJpaImpl implements IMembreService {
    @Autowired
    private MembreJpaRepository membreJpaRepository;


    @Override
    public List<Membre> consulterMembres() {
        return membreJpaRepository.findAll();
    }

    @Override
    public Optional<Membre> consulterMembreParId(long id) {
        return membreJpaRepository.findById(id);
    }
}
