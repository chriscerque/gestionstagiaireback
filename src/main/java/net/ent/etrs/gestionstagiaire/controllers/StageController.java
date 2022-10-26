package net.ent.etrs.gestionstagiaire.controllers;


import lombok.extern.apachecommons.CommonsLog;
import net.ent.etrs.gestionstagiaire.model.entities.Stage;
import net.ent.etrs.gestionstagiaire.model.repo.StageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/stages")
@CommonsLog(topic = "SOUT")
public class StageController {

    @Autowired
    private StageRepo stageRepo;

    @GetMapping(produces = "application/json;charset=utf-8", path = "/")
    public List<Stage> getStages() {
        log.trace("getStages");
        stageRepo.findAll().forEach(s -> log.trace(String.format("%s : %s | %s%n", s.getCodeStage(), s.getDateDebut(), s.getDateFin())));

        return stageRepo.findAll();
    }


}
