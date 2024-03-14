package fr.eni.filmotheque.controller;

import fr.eni.filmotheque.bll.IFilmService;
import fr.eni.filmotheque.bo.Membre;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/membres")
public class MembreController {
    @Autowired
    private IFilmService filmService;

    List<Membre> listeMembres = new ArrayList<>();

    @GetMapping
    public String getMembres(Model model) {
        model.addAttribute("membre", new Membre());
        listeMembres = filmService.consulterMembres();
        model.addAttribute("membres", listeMembres);

        return "membres";
    }

    @PostMapping
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
}
