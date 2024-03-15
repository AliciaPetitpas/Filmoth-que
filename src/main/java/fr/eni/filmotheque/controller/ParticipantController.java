package fr.eni.filmotheque.controller;

import fr.eni.filmotheque.bll.IFilmService;
import fr.eni.filmotheque.bo.Participant;
import jakarta.servlet.http.Part;
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
    private IFilmService filmService;

    List<Participant> listeParticipants = new ArrayList<>();

    @GetMapping("/participants")
    public String getParticipants(Model model) {
        model.addAttribute("participant", new Participant());
        listeParticipants = filmService.consulterParticipants();
        model.addAttribute("participants", listeParticipants);

        return "participants";
    }

    @PostMapping("/participants")
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
        listeParticipants = filmService.consulterParticipants();

        participant.setId((long) (listeParticipants.size() + 1));
        listeParticipants.add(participant);

        return "redirect:/participants";
    }

    @GetMapping("/participantUpdate/{id}")
    public String getUpdateParticipant(@PathVariable("id") long id, Model model) {
        model.addAttribute("participant", filmService.consulterParticipantParId(id));

        return "participantUpdate";
    }

    @PostMapping("/participantUpdate/{id}")
    public String postUpdateParticipant(@PathVariable("id") long id, @Valid Participant participant, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("participant", filmService.consulterParticipantParId(id));
            return "participantUpdate";
        }

        Participant participantAUpdate = filmService.consulterParticipantParId(id);
        participantAUpdate.setNom(participant.Nom);
        participantAUpdate.setPrenom(participant.Prenom);

        listeParticipants = filmService.consulterParticipants();

        return "redirect:/participants";
    }
}
