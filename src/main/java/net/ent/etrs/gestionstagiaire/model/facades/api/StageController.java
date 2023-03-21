package net.ent.etrs.gestionstagiaire.model.facades.api;


import lombok.extern.apachecommons.CommonsLog;
import net.ent.etrs.gestionstagiaire.model.facades.FacadeStage;
import net.ent.etrs.gestionstagiaire.model.facades.api.dto.DtoUtils;
import net.ent.etrs.gestionstagiaire.model.facades.api.dto.StageDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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


}
