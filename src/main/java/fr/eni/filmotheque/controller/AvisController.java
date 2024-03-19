package fr.eni.filmotheque.controller;

import fr.eni.filmotheque.bll.IFilmService;
import fr.eni.filmotheque.bo.Avis;
import fr.eni.filmotheque.security.Utilisateur;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AvisController {
    @Autowired
    private IFilmService filmService;

    List<Avis> listeAvis = new ArrayList<>();

    @GetMapping("/avisCreation/{id}")
    public String getCreationAvis(@PathVariable("id") long id, Model model) {
        model.addAttribute("film", filmService.consulterFilmParId(id));
        model.addAttribute("avis", new Avis());

        return "avisCreation";
    }

    @PostMapping("/avisCreation/{id}")
    public String postCreationAvis(@PathVariable("id") long id, @Valid Avis avis, @AuthenticationPrincipal Utilisateur userConnected, BindingResult bindingResult, Model model) {
        model.addAttribute("avis", new Avis());

        if (bindingResult.hasErrors()) {
            model.addAttribute("films", filmService.consulterFilms());

            return "films";
        }

        listeAvis = filmService.consulterAvis(id);

        avis.setMembre(userConnected.getMembre());
        avis.setId((long) (listeAvis.size() + 1));
        listeAvis.add(avis);

        filmService.consulterFilmParId(id).getAvis().add(avis);

        model.addAttribute("films", filmService.consulterFilms());

        return "redirect:/films";
    }
}
