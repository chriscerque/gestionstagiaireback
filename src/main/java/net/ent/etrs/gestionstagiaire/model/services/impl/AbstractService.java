package net.ent.etrs.gestionstagiaire.model.services.impl;


import net.ent.etrs.gestionstagiaire.model.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class AbstractService {


    @Autowired
    protected StageRepo stageRepo;

    @Autowired
    protected StagiaireRepo stagiaireRepo;
    @Autowired
    protected FormateurRepo formateurRepo;
    @Autowired
    protected EvaluationRepo evaluationRepo;
    @Autowired
    protected IngenierieFormationRepo ingenierieFormationRepo;
    @Autowired
    protected MatiereRepo matiereRepo;
    @Autowired
    protected NoteRepo noteRepo;
    @Autowired
    protected UniteValeurRepo uniteValeurRepo;


//    @Autowired
//    protected AbstractFacade(StageRepo stageRepo, StagiaireRepo stagiaireRepo, FormateurRepo formateurRepo, EvaluationRepo evaluationRepo, IngenierieFormationRepo ingenierieFormationRepo, MatiereRepo matiereRepo, NoteRepo noteRepo, UniteValeurRepo uniteValeurRepo) {
//        this.stageRepo = stageRepo;
//        this.stagiaireRepo = stagiaireRepo;
//        this.formateurRepo = formateurRepo;
//        this.evaluationRepo = evaluationRepo;
//        this.ingenierieFormationRepo = ingenierieFormationRepo;
//        this.matiereRepo = matiereRepo;
//        this.noteRepo = noteRepo;
//        this.uniteValeurRepo = uniteValeurRepo;
//    }
}
