package net.ent.etrs.gestionstagiaire.model.facades.api;


import lombok.extern.apachecommons.CommonsLog;
import net.ent.etrs.gestionstagiaire.model.entities.Stagiaire;
import net.ent.etrs.gestionstagiaire.model.facades.FacadeStagiaire;
import net.ent.etrs.gestionstagiaire.model.facades.api.dto.DtoUtils;
import net.ent.etrs.gestionstagiaire.model.facades.api.dto.StagiaireDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/stagiaires")
@CommonsLog(topic = "SOUT")
public class StagiaireController {

    private FacadeStagiaire facadeStagiaire;

    public StagiaireController(FacadeStagiaire facadeStagiaire) {

        this.facadeStagiaire = facadeStagiaire;
    }

    @GetMapping(produces = "application/json;charset=utf-8", path = "/")
    public ResponseEntity<List<StagiaireDto>> getStagiaire() {

        try {
            return ResponseEntity.ok(facadeStagiaire.findAll().stream().map(s -> DtoUtils.stagiaireToDto(s)).collect(Collectors.toList()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

    }

    @PostMapping(path = "/", produces = "application/json;charset=utf-8", consumes = "application/json;charset=utf-8")
    public ResponseEntity<StagiaireDto> create(@RequestBody @Valid StagiaireDto stagiaireDto) {
        log.trace("StagiaireController / create");
        log.trace("stagiaire : " + stagiaireDto);


//        try {
        Optional<Stagiaire> oStagiaire = facadeStagiaire.save(DtoUtils.stagiaireFromDto(stagiaireDto));
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


        if (!this.facadeStagiaire.exist(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        log.trace("StagiaireController / upadte 111");
        Stagiaire stagiaire = DtoUtils.stagiaireFromDto(stagiaireDto);
        log.trace("StagiaireController / upadte 222");
        Stagiaire s = this.facadeStagiaire.save(stagiaire).orElseThrow();
        log.trace("StagiaireController / upadte 333");
        return ResponseEntity.ok(DtoUtils.stagiaireToDto(s));


    }

    @GetMapping(produces = "application/json; charset=UTF-8", path = "/{id}")
    public ResponseEntity<StagiaireDto> findById(@PathVariable("id") Long id) {
        try {
            Optional<Stagiaire> oStagiaire = this.facadeStagiaire.findById(id);
            if (oStagiaire.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(DtoUtils.stagiaireToDto(oStagiaire.get()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
