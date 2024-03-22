package fr.eni.filmotheque.api;

import fr.eni.filmotheque.bll.IMembreService;
import fr.eni.filmotheque.bo.Membre;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/membres")
public class MembreRestController {
    @Autowired
    private IMembreService membreService;

    @GetMapping
    public List<Membre> getMembres() {
        return membreService.consulterMembres();
    }

    @GetMapping("/{id}")
    public Membre getMembre(@PathVariable("id") long id) {
        return membreService.consulterMembreParId(id);
    }

    @PostMapping
    public void postMembre(@RequestBody @Valid Membre membre) throws Exception {
        membreService.enregistrerMembre(membre);
    }

    @PutMapping("/{id}")
    public void putMembre(@PathVariable("id") long id, @RequestBody @Valid Membre membre) throws Exception {
        membre.setId(id);

        membreService.enregistrerMembre(membre);
    }

    @DeleteMapping("/{id}")
    public void deleteMembre(@PathVariable("id") long id) {
        membreService.supprimerMembreParId(id);
    }
}
