package net.ent.etrs.gestionstagiaire.model.facades.impl;

import lombok.extern.apachecommons.CommonsLog;
import net.ent.etrs.gestionstagiaire.model.entities.Note;
import net.ent.etrs.gestionstagiaire.model.facades.FacaceNote;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@CommonsLog(topic = "SOUT")
public class FacadeNoteImpl extends AbstractFacade implements FacaceNote {
//    protected FacadeNoteImpl(StageRepo stageRepo, StagiaireRepo stagiaireRepo, FormateurRepo formateurRepo, EvaluationRepo evaluationRepo, IngenierieFormationRepo ingenierieFormationRepo, MatiereRepo matiereRepo, NoteRepo noteRepo, UniteValeurRepo uniteValeurRepo) {
//        super(stageRepo, stagiaireRepo, formateurRepo, evaluationRepo, ingenierieFormationRepo, matiereRepo, noteRepo, uniteValeurRepo);
//    }

    @Override
    public Optional<Note> findById(Long id) {
        return this.noteRepo.findById(id);
    }

    @Override
    public List<Note> findAllById(List<Long> notes) {
        return this.noteRepo.findAllById(notes);
    }
}
