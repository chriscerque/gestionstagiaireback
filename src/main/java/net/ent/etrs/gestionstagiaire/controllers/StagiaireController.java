package net.ent.etrs.gestionstagiaire.controllers;


import net.ent.etrs.gestionstagiaire.controllers.dto.DtoUtils;
import net.ent.etrs.gestionstagiaire.controllers.dto.StagiaireDto;
import net.ent.etrs.gestionstagiaire.model.entities.Stagiaire;
import net.ent.etrs.gestionstagiaire.model.repo.StagiaireRepo;
import net.ent.etrs.gestionstagiaire.model.services.IStagiaireFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

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
            return ResponseEntity.ok(stagiaireFacade.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

//        stagiaireRepo.findAll().forEach(s -> System.out.printf("%s : %s | %s%n", s.getNom(), s.getPrenom(), s.getAppartenance()));
//
//        return stagiaireRepo.findAll();
    }

    @PostMapping(path = "/", produces = "application/json;charset=utf-8", consumes = "application/json;charset=utf-8")
    public ResponseEntity<StagiaireDto> create(@RequestBody StagiaireDto stagiaireDto) {
        System.out.println("StagiaireController / setStagiaire");
        System.out.println("stagiaire : " + stagiaireDto);


        try {
            Optional<Stagiaire> oStagiaire = stagiaireFacade.save(DtoUtils.stagiaireFromDto(stagiaireDto));
            if (oStagiaire.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

            }
            return ResponseEntity.ok(DtoUtils.stagiaireToDto(oStagiaire.get()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping(path = "/{id}", produces = "application/json;charset=utf-8", consumes = "application/json;charset=utf-8")
    public ResponseEntity<StagiaireDto> upadte(@PathParam("id") Long id, @RequestBody StagiaireDto stagiaireDto) {
        System.out.println("StagiaireController / setStagiaire");
        System.out.println("stagiaire : " + stagiaireDto);


        try {
            if (!this.stagiaireFacade.exist(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            Stagiaire stagiaire = DtoUtils.stagiaireFromDto(stagiaireDto);
            Stagiaire s = this.stagiaireFacade.save(stagiaire).orElseThrow(Exception::new);
            return ResponseEntity.ok(DtoUtils.stagiaireToDto(s));

//            Optional<Stagiaire> oStagiaire = stagiaireFacade.save(DtoUtils.stagiaireFromDto(stagiaireDto));
//            if (oStagiaire.isEmpty()) {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//
//            }
//            return ResponseEntity.ok(DtoUtils.stagiaireToDto(oStagiaire.get()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(produces = "application/json; charset=UTF-8", path = "/{id}")
    public ResponseEntity<StagiaireDto> findById(@PathParam("id") Long id) {
        try {
            Optional<Stagiaire> oStagiaire = this.stagiaireFacade.findById(id);
            if (oStagiaire.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(DtoUtils.stagiaireToDto(oStagiaire.get()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
