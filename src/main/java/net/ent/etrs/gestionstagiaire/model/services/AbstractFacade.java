package net.ent.etrs.gestionstagiaire.model.services;


import net.ent.etrs.gestionstagiaire.model.repo.FormateurRepo;
import net.ent.etrs.gestionstagiaire.model.repo.StageRepo;
import net.ent.etrs.gestionstagiaire.model.repo.StagiaireRepo;
import org.springframework.stereotype.Service;

@Service
public abstract class AbstractFacade {

    protected StageRepo stageRepo;
    protected StagiaireRepo stagiaireRepo;
    protected FormateurRepo formateurRepo;

    protected AbstractFacade(StageRepo stageRepo, StagiaireRepo stagiaireRepo, FormateurRepo formateurRepo) {
        this.stageRepo = stageRepo;
        this.stagiaireRepo = stagiaireRepo;
        this.formateurRepo = formateurRepo;
    }
}
