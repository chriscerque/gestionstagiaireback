package net.ent.etrs.gestionstagiaire.controllers;


import net.ent.etrs.gestionstagiaire.model.entities.Stagiaire;
import net.ent.etrs.gestionstagiaire.model.services.IStagiaireFacade;
import net.ent.etrs.gestionstagiaire.controllers.dto.DtoUtils;
import net.ent.etrs.gestionstagiaire.controllers.dto.StagiaireDto;
import net.ent.etrs.gestionstagiaire.model.repo.StagiaireRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/stagiaires")

public class StagiaireController {

    private StagiaireRepo stagiaireRepo;

    private IStagiaireFacade stagiaireFacade;

    public StagiaireController(StagiaireRepo stagiaireRepo, IStagiaireFacade stagiaireFacade) {
        this.stagiaireRepo = stagiaireRepo;
        this.stagiaireFacade = stagiaireFacade;
    }

    @GetMapping(produces = "application/json;charset=utf-8", path = "/")
    public ResponseEntity<List<Stagiaire>> getStagiaire() {

        try {
            return ResponseEntity.ok(stagiaireFacade.readAllStagiaire());
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

//        stagiaireRepo.findAll().forEach(s -> System.out.printf("%s : %s | %s%n", s.getNom(), s.getPrenom(), s.getAppartenance()));
//
//        return stagiaireRepo.findAll();
    }

    @PostMapping(produces = "application/json;charset=utf-8", path = "/")
    public ResponseEntity<?> setStagiaire(@RequestBody StagiaireDto stagiaireDto) {
        System.out.println("StagiaireController / setStagiaire");
        System.out.println("stagiaire : " + stagiaireDto);


        try {
            return ResponseEntity.ok(stagiaireFacade.saveStagiaire(DtoUtils.stagiaireFromDto(stagiaireDto)));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

}
