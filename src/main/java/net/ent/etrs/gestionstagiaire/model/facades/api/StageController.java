package net.ent.etrs.gestionstagiaire.model.facades.api;


import lombok.extern.apachecommons.CommonsLog;
import net.ent.etrs.gestionstagiaire.model.entities.Stage;
import net.ent.etrs.gestionstagiaire.model.facades.FacadeStage;
import net.ent.etrs.gestionstagiaire.model.facades.api.dto.DtoUtils;
import net.ent.etrs.gestionstagiaire.model.facades.api.dto.StageDto;
import net.ent.etrs.gestionstagiaire.model.facades.exceptions.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/stages")
@CommonsLog(topic = "SOUT")
public class StageController {

    private FacadeStage facadeStage;

    public StageController(FacadeStage facadeStage) {
        this.facadeStage = facadeStage;
    }

    @GetMapping(produces = "application/json;charset=utf-8", path = "/")
    public ResponseEntity<List<StageDto>> getStages() {
        log.trace("getStages");
//        stageRepo.findAll().forEach(s -> log.trace(String.format("%s : %s | %s%n", s.getCodeStage(), s.getDateDebut(), s.getDateFin())));
//
//        return stageRepo.findAll();
        try {
            return ResponseEntity.ok(facadeStage.findAll().stream().map(s -> DtoUtils.stageToDto(s)).collect(Collectors.toList()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

    }

    @PostMapping(path = "/", produces = "application/json;charset=utf-8", consumes = "application/json;charset=utf-8")
    public ResponseEntity<StageDto> create(@RequestBody @Valid StageDto stageDto) {
        log.trace("StageController / create");
        log.trace("stage : " + stageDto);


        try {
            Optional<Stage> oStage = facadeStage.save(DtoUtils.stageFromDto(stageDto));
            if (oStage.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

            }
            return ResponseEntity.ok(DtoUtils.stageToDto(oStage.get()));
        } catch (Exception | BusinessException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
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
    public ResponseEntity<StageDto> upadte(@PathVariable("id") Long id, @RequestBody @Valid StageDto stageDto) {
        try {
            log.trace("StagiaireController / setStagiaire");
            log.trace("stagiaire : " + stageDto);

            log.trace("StagiaireController / upadte id : " + id);


            if (!this.facadeStage.exist(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            log.trace("StagiaireController / upadte 111");
            Stage stage = DtoUtils.stageFromDto(stageDto);
            log.trace("StagiaireController / upadte 222");
            Stage s = this.facadeStage.save(stage).orElseThrow();
            log.trace("StagiaireController / upadte 333");
            return ResponseEntity.ok(DtoUtils.stageToDto(s));
        } catch (Exception | BusinessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @GetMapping(produces = "application/json; charset=UTF-8", path = "/{id}")
    public ResponseEntity<StageDto> findById(@PathVariable("id") Long id) {
        try {
            Optional<Stage> oStage = this.facadeStage.findById(id);
            if (oStage.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(DtoUtils.stageToDto(oStage.get()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
