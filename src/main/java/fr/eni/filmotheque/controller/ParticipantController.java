package fr.eni.filmotheque.controller;

import fr.eni.filmotheque.bll.IFilmService;
import fr.eni.filmotheque.bo.Participant;
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
@RequestMapping("/participants")
public class ParticipantController {
    @Autowired
    private IFilmService filmService;

    List<Participant> listeParticipants = new ArrayList<>();

    @GetMapping
    public String getParticipants(Model model) {
        model.addAttribute("participant", new Participant());
        listeParticipants = filmService.consulterParticipants();
        model.addAttribute("participants", listeParticipants);

        return "participants";
    }

    @PostMapping
    public String postParticipants(@Valid Participant participant, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            listeParticipants = filmService.consulterParticipants();
            model.addAttribute("participants", listeParticipants);
            return "participants";
        }

        participant.setId((long) (listeParticipants.size() + 1));

        listeParticipants.add(participant);

        return "redirect:/participants";
    }
}
