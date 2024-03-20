package fr.eni.filmotheque.controller;

import fr.eni.filmotheque.bll.IParticipantService;
import fr.eni.filmotheque.bo.Participant;
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
public class ParticipantController {
    @Autowired
    private IParticipantService participantService;

    @GetMapping("/participants")
    public String getParticipants(Model model) {
        model.addAttribute("participant", new Participant());
        model.addAttribute("participants", participantService.consulterParticipants());

        return "participants";
    }

    @GetMapping("/participantCreation")
    public String getCreationParticipant(Model model) {
        model.addAttribute("participant", new Participant());

        return "participantCreation";
    }

    @PostMapping("/participantCreation")
    public String postCreationParticipant(@Valid Participant participant, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("participant", new Participant());
            return "participantCreation";
        }

        participantService.enregistrerParticipant(participant);

        return "redirect:/participants";
    }

    @GetMapping("/participantUpdate/{id}")
    public String getUpdateParticipant(@PathVariable("id") long id, Model model) {
        model.addAttribute("participant", participantService.consulterParticipantParId(id));
        model.addAttribute("id", id);

        return "participantUpdate";
    }

    @PostMapping("/participantUpdate/{id}")
    public String postUpdateParticipant(@PathVariable("id") long id, @Valid Participant participant, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("participant", participantService.consulterParticipantParId(id));
            model.addAttribute("id", id);
            return "participantUpdate";
        }

        participantService.modifierParticipant(participant);

        return "redirect:/participants";
    }

    @PostMapping("/participantDelete/{id}")
    public String postDeleteParticipant(@PathVariable("id") long id) {
        participantService.supprimerParticipantParId(id);

        return "redirect:/participants";
    }
}
