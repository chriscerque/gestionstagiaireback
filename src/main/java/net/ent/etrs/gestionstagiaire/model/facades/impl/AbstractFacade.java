package net.ent.etrs.gestionstagiaire.model.facades.impl;


import net.ent.etrs.gestionstagiaire.model.repo.*;
import org.springframework.stereotype.Service;

@Service
public abstract class AbstractFacade {

    protected StageRepo stageRepo;
    protected StagiaireRepo stagiaireRepo;
    protected FormateurRepo formateurRepo;
    protected EvaluationRepo evaluationRepo;
    protected IngenierieFormationRepo ingenierieFormationRepo;
    protected MatiereRepo matiereRepo;
    protected NoteRepo noteRepo;
    protected UniteValeurRepo uniteValeurRepo;

    protected AbstractFacade(StageRepo stageRepo, StagiaireRepo stagiaireRepo, FormateurRepo formateurRepo, EvaluationRepo evaluationRepo, IngenierieFormationRepo ingenierieFormationRepo, MatiereRepo matiereRepo, NoteRepo noteRepo, UniteValeurRepo uniteValeurRepo) {
        this.stageRepo = stageRepo;
        this.stagiaireRepo = stagiaireRepo;
        this.formateurRepo = formateurRepo;
        this.evaluationRepo = evaluationRepo;
        this.ingenierieFormationRepo = ingenierieFormationRepo;
        this.matiereRepo = matiereRepo;
        this.noteRepo = noteRepo;
        this.uniteValeurRepo = uniteValeurRepo;
    }
}
