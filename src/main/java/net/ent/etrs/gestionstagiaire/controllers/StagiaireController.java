package net.ent.etrs.gestionstagiaire.controllers;


import lombok.extern.apachecommons.CommonsLog;
import net.ent.etrs.gestionstagiaire.controllers.dto.DtoUtils;
import net.ent.etrs.gestionstagiaire.controllers.dto.StagiaireDto;
import net.ent.etrs.gestionstagiaire.model.entities.Stagiaire;
import net.ent.etrs.gestionstagiaire.model.services.IStagiaireFacade;
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

    private IStagiaireFacade stagiaireFacade;

    public StagiaireController(IStagiaireFacade stagiaireFacade) {

        this.stagiaireFacade = stagiaireFacade;
    }

    @GetMapping(produces = "application/json;charset=utf-8", path = "/")
    public ResponseEntity<List<Stagiaire>> getStagiaire() {

        try {
            return ResponseEntity.ok(stagiaireFacade.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

    }

    @PostMapping(path = "/", produces = "application/json;charset=utf-8", consumes = "application/json;charset=utf-8")
    public ResponseEntity<StagiaireDto> create(@RequestBody @Valid StagiaireDto stagiaireDto) {
        log.trace("StagiaireController / create");
        log.trace("stagiaire : " + stagiaireDto);


//        try {
        Optional<Stagiaire> oStagiaire = stagiaireFacade.save(DtoUtils.stagiaireFromDto(stagiaireDto));
        if (oStagiaire.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }
        return ResponseEntity.ok(DtoUtils.stagiaireToDto(oStagiaire.get()));
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
        log.trace("StagiaireController / setStagiaire");
        log.trace("stagiaire : " + stagiaireDto);

        log.trace("StagiaireController / upadte id : " + id);


        if (!this.stagiaireFacade.exist(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        log.trace("StagiaireController / upadte 111");
        Stagiaire stagiaire = DtoUtils.stagiaireFromDto(stagiaireDto);
        log.trace("StagiaireController / upadte 222");
        Stagiaire s = this.stagiaireFacade.save(stagiaire).orElseThrow();
        log.trace("StagiaireController / upadte 333");
        return ResponseEntity.ok(DtoUtils.stagiaireToDto(s));


    }

    @GetMapping(produces = "application/json; charset=UTF-8", path = "/{id}")
    public ResponseEntity<StagiaireDto> findById(@PathVariable("id") Long id) {
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
