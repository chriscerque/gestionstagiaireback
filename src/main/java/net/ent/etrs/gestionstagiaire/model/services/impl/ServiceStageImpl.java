package net.ent.etrs.gestionstagiaire.model.services.impl;

import lombok.extern.apachecommons.CommonsLog;
import net.ent.etrs.gestionstagiaire.model.entities.Stage;
import net.ent.etrs.gestionstagiaire.model.services.ServiceStage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@CommonsLog(topic = "SOUT")
public class ServiceStageImpl extends AbstractService implements ServiceStage {


//    protected FacadeStageImpl(StageRepo stageRepo, StagiaireRepo stagiaireRepo, FormateurRepo formateurRepo, EvaluationRepo evaluationRepo, IngenierieFormationRepo ingenierieFormationRepo, MatiereRepo matiereRepo, NoteRepo noteRepo, UniteValeurRepo uniteValeurRepo) {
//        super(stageRepo, stagiaireRepo, formateurRepo, evaluationRepo, ingenierieFormationRepo, matiereRepo, noteRepo, uniteValeurRepo);
//    }

    @Override
    public Optional<Stage> save(Stage stage) {
        if (stage.getId() != null) {
            log.trace("StagiaireFacade.save id not null ");
            log.trace("stagiaire : " + stage);
            return Optional.of(this.stageRepo.save(stage));
        } else {
            log.trace("StagiaireFacade.save id null ");
            return Optional.of(this.stageRepo.save(stage));
        }
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
    public Optional<Stage> findById(Long id) {
        return this.stageRepo.findById(id);
    }

    @Override
    public Optional<Stage> findByCodeStage(String codeStage) {
        return this.stageRepo.findByCodeStage(codeStage);
    }

    @Override
    public boolean exist(Long id) {
        return super.stageRepo.existsById(id);
    }

    @Override
    public Long count() {
        return super.stageRepo.count();
    }
}
