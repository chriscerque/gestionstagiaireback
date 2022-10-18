package net.ent.etrs.gestionstagiaire.model.facades.api;


import net.ent.etrs.gestionstagiaire.model.entities.Stagiaire;
import net.ent.etrs.gestionstagiaire.model.facades.IStagiaireFacade;
import net.ent.etrs.gestionstagiaire.model.facades.api.dto.DtoUtils;
import net.ent.etrs.gestionstagiaire.model.facades.api.dto.StagiaireDto;
import net.ent.etrs.gestionstagiaire.model.repo.StagiaireRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/")
public class StagiaireController {

    @Autowired
    private StagiaireRepo stagiaireRepo;

    @Autowired
    private IStagiaireFacade stagiaireFacade;


    @GetMapping(produces = "application/json;charset=utf-8", path = "/stagiaires")
    public List<Stagiaire> getStagiaire() {

        stagiaireRepo.findAll().forEach(s -> System.out.printf("%s : %s | %s%n", s.getNom(), s.getPrenom(), s.getAppartenance()));

        return stagiaireRepo.findAll();
    }

    @PostMapping(produces = "application/json;charset=utf-8", path = "/stagiaire")
    public ResponseEntity<?> setStagiaire(@RequestBody StagiaireDto stagiaireDto) {
        System.out.println("StagiaireController / setStagiaire");
        System.out.println("stagiaire : " + stagiaireDto);


        return ResponseEntity.ok(stagiaireRepo.save(DtoUtils.stagiaireFromDto(stagiaireDto)));
    }

}
