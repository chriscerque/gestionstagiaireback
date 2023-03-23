package net.ent.etrs.gestionstagiaire.controller;


import lombok.extern.apachecommons.CommonsLog;
import net.ent.etrs.gestionstagiaire.controller.dto.StagiaireDto;
import net.ent.etrs.gestionstagiaire.model.services.ServiceStagiaire;
import net.ent.etrs.gestionstagiaire.model.services.exceptions.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/stagiaires")
@CommonsLog(topic = "SOUT")
public class StagiaireController {

    private ServiceStagiaire serviceStagiaire;

    public StagiaireController(ServiceStagiaire serviceStagiaire) {

        this.serviceStagiaire = serviceStagiaire;
    }

    @GetMapping(produces = "application/json;charset=utf-8", path = "/")
    public ResponseEntity<List<StagiaireDto>> getStagiaire() {

        try {
            return ResponseEntity.ok(serviceStagiaire.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

    }

    @PostMapping(path = "/", produces = "application/json;charset=utf-8", consumes = "application/json;charset=utf-8")
    public ResponseEntity<StagiaireDto> create(@RequestBody @Valid StagiaireDto stagiaireDto) {
//        try {
        Optional<StagiaireDto> oStagiaire = serviceStagiaire.save(stagiaireDto);
        if (oStagiaire.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(oStagiaire.get());
//        } catch (ConstraintViolationException e) {
//            System.out.println("StagiaireController create failed ConstraintViolationException : " + e.getMessage());
//            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
//        } catch (DataIntegrityViolationException e) {
//            System.out.println("StagiaireController create failed DataIntegrityViolationException : " + e.getMessage());
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
    }

    @PutMapping(path = "/{id}", produces = "application/json;charset=utf-8", consumes = "application/json;charset=utf-8")
    public ResponseEntity<StagiaireDto> upadte(@PathVariable("id") Long id, @RequestBody @Valid StagiaireDto stagiaireDto) {
        if (!this.serviceStagiaire.exist(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        StagiaireDto s = this.serviceStagiaire.save(stagiaireDto).orElseThrow();

        return ResponseEntity.ok(s);
    }

    @GetMapping(produces = "application/json; charset=UTF-8", path = "/{id}")
    public ResponseEntity<StagiaireDto> findById(@PathVariable("id") Long id) {
        try {
            Optional<StagiaireDto> oStagiaire = this.serviceStagiaire.findById(id);
            if (oStagiaire.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(oStagiaire.get());
        } catch (Exception | BusinessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
