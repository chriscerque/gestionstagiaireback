package net.ent.etrs.gestionstagiaire.controllers;


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
        System.out.println("StagiaireController / create");
        System.out.println("stagiaire : " + stagiaireDto);


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
        System.out.println("StagiaireController / setStagiaire");
        System.out.println("stagiaire : " + stagiaireDto);

        System.out.println("StagiaireController / upadte id : " + id);

//        try {
        if (!this.stagiaireFacade.exist(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        System.out.println("StagiaireController / upadte 111");
        Stagiaire stagiaire = DtoUtils.stagiaireFromDto(stagiaireDto);
        System.out.println("StagiaireController / upadte 222");
//            Stagiaire s = this.stagiaireFacade.save(stagiaire).orElseThrow(Exception::new);
        Stagiaire s = this.stagiaireFacade.save(stagiaire).get();
        System.out.println("StagiaireController / upadte 333");
        return ResponseEntity.ok(DtoUtils.stagiaireToDto(s));

//            Optional<Stagiaire> oStagiaire = stagiaireFacade.save(DtoUtils.stagiaireFromDto(stagiaireDto));
//            if (oStagiaire.isEmpty()) {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//
//            }
//            return ResponseEntity.ok(DtoUtils.stagiaireToDto(oStagiaire.get()));
//        } catch (Exception e) {
////            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
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
