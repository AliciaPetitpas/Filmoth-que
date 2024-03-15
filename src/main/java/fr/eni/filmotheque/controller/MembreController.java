package fr.eni.filmotheque.controller;

import fr.eni.filmotheque.bll.IFilmService;
import fr.eni.filmotheque.bo.Genre;
import fr.eni.filmotheque.bo.Membre;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MembreController {
    @Autowired
    private IFilmService filmService;

    List<Membre> listeMembres = new ArrayList<>();

    @GetMapping("/membres")
    public String getMembres(Model model) {
        model.addAttribute("membre", new Membre());
        listeMembres = filmService.consulterMembres();
        model.addAttribute("membres", listeMembres);

        return "membres";
    }

    @PostMapping("/membres")
    public String postMembres(@Valid Membre membre, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult);
            listeMembres = filmService.consulterMembres();
            model.addAttribute("membres", listeMembres);
            return "membres";
        }

        membre.setId((long) (listeMembres.size() + 1));

        listeMembres.add(membre);

        return "redirect:/membres";
    }

    @GetMapping("/membreCreation")
    public String getCreationMembre(Model model) {
        model.addAttribute("membre", new Membre());

        return "membreCreation";
    }

    @PostMapping("/membreCreation")
    public String postCreationMembre(@Valid Membre membre, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("membre", new Membre());
            return "membreCreation";
        }
        listeMembres = filmService.consulterMembres();

        membre.setId((long) (listeMembres.size() + 1));
        listeMembres.add(membre);

        return "redirect:/membres";
    }

    @GetMapping("/membreUpdate/{id}")
    public String getUpdateGenre(@PathVariable("id") long id, Model model) {
        model.addAttribute("membre", filmService.consulterMembreParId(id));

        return "membreUpdate";
    }

    @PostMapping("/membreUpdate/{id}")
    public String postUpdateGenre(@PathVariable("id") long id, @Valid Membre membre, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            System.out.println(membre);
            model.addAttribute("membre", filmService.consulterMembreParId(id));
            return "membreUpdate";
        }

        Membre membreAUpdate = filmService.consulterMembreParId(id);
        membreAUpdate.setNom(membre.Nom);
        membreAUpdate.setPrenom(membre.Prenom);
        membreAUpdate.setPseudo(membre.Pseudo);
        membreAUpdate.setAdmin(membre.isAdmin);

        listeMembres = filmService.consulterMembres();

        return "redirect:/membres";
    }
}
