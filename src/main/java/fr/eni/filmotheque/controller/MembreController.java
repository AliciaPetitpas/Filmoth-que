package fr.eni.filmotheque.controller;

import fr.eni.filmotheque.bll.IMembreService;
import fr.eni.filmotheque.bo.Membre;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MembreController {
    @Autowired
    private IMembreService membreService;

    @GetMapping("/membres")
    public String getMembres(Model model) {
        model.addAttribute("membre", new Membre());
        model.addAttribute("membres", membreService.consulterMembres());

        return "membres";
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

        try {
            membreService.enregistrerMembre(membre);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("membres", membreService.consulterMembres());

            return "membres";
        }

        return "redirect:/membres";
    }

    @GetMapping("/membreUpdate/{id}")
    public String getUpdateGenre(@PathVariable("id") long id, Model model) {
        model.addAttribute("membre", membreService.consulterMembreParId(id));
        model.addAttribute("id", id);

        return "membreUpdate";
    }

    @PostMapping("/membreUpdate/{id}")
    public String postUpdateGenre(@PathVariable("id") long id, @Valid Membre membre, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("membre", membreService.consulterMembreParId(id));
            model.addAttribute("id", id);
            return "membreUpdate";
        }

        membreService.modifierMembre(membre);

        return "redirect:/membres";
    }

    @PostMapping("/membreDelete/{id}")
    public String postDeleteMembre(@PathVariable("id") long id) {
        membreService.supprimerMembreParId(id);

        return "redirect:/membres";
    }
}
