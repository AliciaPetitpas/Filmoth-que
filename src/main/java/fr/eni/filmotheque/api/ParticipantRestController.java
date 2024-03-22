package fr.eni.filmotheque.api;

import fr.eni.filmotheque.bll.IParticipantService;
import fr.eni.filmotheque.bo.Participant;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/participants")
public class ParticipantRestController {
    @Autowired
    private IParticipantService participantService;

    @GetMapping
    public List<Participant> getParticipants() {
        return participantService.consulterParticipants();
    }

    @GetMapping("/{id}")
    public Participant getParticipant(@PathVariable("id") long id) {
        return participantService.consulterParticipantParId(id);
    }

    @PostMapping
    public void postParticipant(@RequestBody @Valid Participant participant) {
        participantService.enregistrerParticipant(participant);
    }

    @PutMapping("/{id}")
    public void putParticipant(@PathVariable("id") long id, @RequestBody @Valid Participant participant) {
        participant.setId(id);

        participantService.enregistrerParticipant(participant);
    }

    @DeleteMapping("/{id}")
    public void deleteParticipant(@PathVariable("id") long id) {
        participantService.supprimerParticipantParId(id);
    }
}
