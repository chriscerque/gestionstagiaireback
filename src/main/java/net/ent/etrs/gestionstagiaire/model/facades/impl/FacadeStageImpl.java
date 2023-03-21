package net.ent.etrs.gestionstagiaire.model.facades.impl;

import net.ent.etrs.gestionstagiaire.model.entities.Stage;
import net.ent.etrs.gestionstagiaire.model.facades.FacadeStage;
import net.ent.etrs.gestionstagiaire.model.repo.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacadeStageImpl extends AbstractFacade implements FacadeStage {


    protected FacadeStageImpl(StageRepo stageRepo, StagiaireRepo stagiaireRepo, FormateurRepo formateurRepo, EvaluationRepo evaluationRepo, IngenierieFormationRepo ingenierieFormationRepo, MatiereRepo matiereRepo, NoteRepo noteRepo, UniteValeurRepo uniteValeurRepo) {
        super(stageRepo, stagiaireRepo, formateurRepo, evaluationRepo, ingenierieFormationRepo, matiereRepo, noteRepo, uniteValeurRepo);
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
