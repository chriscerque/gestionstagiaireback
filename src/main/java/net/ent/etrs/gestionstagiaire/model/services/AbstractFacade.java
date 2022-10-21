package net.ent.etrs.gestionstagiaire.model.services;


import net.ent.etrs.gestionstagiaire.model.repo.FormateurRepo;
import net.ent.etrs.gestionstagiaire.model.repo.StageRepo;
import net.ent.etrs.gestionstagiaire.model.repo.StagiaireRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class AbstractFacade {

    @Autowired
    protected StageRepo stageRepo;
    @Autowired
    protected StagiaireRepo stagiaireRepo;
    @Autowired
    protected FormateurRepo formateurRepo;

    protected AbstractFacade() {

    }
}
