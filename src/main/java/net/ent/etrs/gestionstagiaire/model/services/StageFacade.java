package net.ent.etrs.gestionstagiaire.model.services;

import net.ent.etrs.gestionstagiaire.model.entities.Stage;

import java.util.List;
import java.util.Optional;

//@Component
public class StageFacade extends AbstractFacade implements IStageFacade{
    @Override
    public Stage saveStage(Stage stage) {
        Stage st = this.stageRepo.findByCodeStage(stage.getCodeStage());
        if(st == null){
            return this.stageRepo.save(stage);
        }
        return st;
    }

    @Override
    public void deleteStage(Stage stage) {
        this.stageRepo.delete(stage);
    }

    @Override
    public Optional<Stage> readStage(Long id) {
        return this.stageRepo.findById(id);
    }

    @Override
    public List<Stage> readAllStage() {
        return this.stageRepo.findAll();
    }

    @Override
    public Stage readStage(String codeStage) {
        return this.stageRepo.findByCodeStage(codeStage);
    }
}
