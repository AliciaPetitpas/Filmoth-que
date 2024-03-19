package fr.eni.filmotheque.bll.jpa;

import fr.eni.filmotheque.bll.IAvisService;
import fr.eni.filmotheque.bo.Avis;
import fr.eni.filmotheque.dal.AvisJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvisServiceJpaImpl implements IAvisService {

    @Autowired
    private AvisJpaRepository avisJpaRepository;

    @Override
    public List<Avis> consulterAvis(long idFilm) {
        return avisJpaRepository.findAll();
    }

    @Override
    public Avis creerAvis(Avis avis) {
        return avisJpaRepository.save(avis);
    }
}
