package net.ent.etrs.gestionstagiaire.model.facades.impl;

import lombok.extern.apachecommons.CommonsLog;
import net.ent.etrs.gestionstagiaire.model.entities.IngenierieFormation;
import net.ent.etrs.gestionstagiaire.model.facades.FacadeIngenierieFormation;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

@Service
@CommonsLog(topic = "SOUT")
public class FacadeIngenierieFormationImpl extends AbstractFacade implements FacadeIngenierieFormation {

//    protected FacadeIngenierieFormationImpl(StageRepo stageRepo, StagiaireRepo stagiaireRepo, FormateurRepo formateurRepo, EvaluationRepo evaluationRepo, IngenierieFormationRepo ingenierieFormationRepo, MatiereRepo matiereRepo, NoteRepo noteRepo, UniteValeurRepo uniteValeurRepo) {
//        super(stageRepo, stagiaireRepo, formateurRepo, evaluationRepo, ingenierieFormationRepo, matiereRepo, noteRepo, uniteValeurRepo);
//    }


    @Override
    public Optional<IngenierieFormation> save(IngenierieFormation ingenierieFormation) throws DataIntegrityViolationException, ConstraintViolationException {
//        try {

        if (ingenierieFormation.getId() != null) {
            log.trace("IngenierieFormationFacade.save id not null ");
            log.trace("ingenierieFormation : " + ingenierieFormation);
            return Optional.of(this.ingenierieFormationRepo.save(ingenierieFormation));
        } else {
            log.trace("StagiaireFacade.save id null ");
            return Optional.of(this.ingenierieFormationRepo.save(ingenierieFormation));
        }

    }

    @Override
    public void delete(IngenierieFormation ingenierieFormation) {
        this.ingenierieFormationRepo.delete(ingenierieFormation);
    }

    @Override
    public Optional<IngenierieFormation> findById(Long id) {
        return this.ingenierieFormationRepo.findById(id);
    }

    @Override
    public List<IngenierieFormation> findAll() {
        return super.ingenierieFormationRepo.findAll();
    }

    @Override
    public Optional<IngenierieFormation> findByLibelle(String libelle) {
        return null;
    }

    @Override
    public boolean exist(Long id) {
        return super.ingenierieFormationRepo.existsById(id);
    }


}
