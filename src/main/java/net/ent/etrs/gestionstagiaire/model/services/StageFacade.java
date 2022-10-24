package net.ent.etrs.gestionstagiaire.model.services;

import net.ent.etrs.gestionstagiaire.model.entities.Stage;
import net.ent.etrs.gestionstagiaire.model.repo.FormateurRepo;
import net.ent.etrs.gestionstagiaire.model.repo.StageRepo;
import net.ent.etrs.gestionstagiaire.model.repo.StagiaireRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StageFacade extends AbstractFacade implements IStageFacade {
    protected StageFacade(StageRepo stageRepo, StagiaireRepo stagiaireRepo, FormateurRepo formateurRepo) {
        super(stageRepo, stagiaireRepo, formateurRepo);
    }

    @Override
    public Stage save(Stage stage) {
        Stage st = this.stageRepo.findByCodeStage(stage.getCodeStage());
        if (st == null) {
            return this.stageRepo.save(stage);
        }
        return st;
    }

    @Override
    public void delete(Stage stage) {
        this.stageRepo.delete(stage);
    }

    @Override
    public Optional<Stage> findByCodeStage(Long id) {
        return this.stageRepo.findById(id);
    }

    @Override
    public List<Stage> findAll() {
        return this.stageRepo.findAll();
    }

    @Override
    public Stage findByCodeStage(String codeStage) {
        return this.stageRepo.findByCodeStage(codeStage);
    }
}
