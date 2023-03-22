package net.ent.etrs.gestionstagiaire.model.facades.impl;

import lombok.extern.apachecommons.CommonsLog;
import net.ent.etrs.gestionstagiaire.model.entities.Stagiaire;
import net.ent.etrs.gestionstagiaire.model.facades.FacadeStagiaire;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

@Service
@CommonsLog(topic = "SOUT")
public class FacadeStagiaireImpl extends AbstractFacade implements FacadeStagiaire {


//    protected FacadeStagiaireImpl(StageRepo stageRepo, StagiaireRepo stagiaireRepo, FormateurRepo formateurRepo, EvaluationRepo evaluationRepo, IngenierieFormationRepo ingenierieFormationRepo, MatiereRepo matiereRepo, NoteRepo noteRepo, UniteValeurRepo uniteValeurRepo) {
//        super(stageRepo, stagiaireRepo, formateurRepo, evaluationRepo, ingenierieFormationRepo, matiereRepo, noteRepo, uniteValeurRepo);
//    }

    @Override
    public Optional<Stagiaire> save(Stagiaire stagiaire) throws DataIntegrityViolationException, ConstraintViolationException {
//        try {

        if (stagiaire.getId() != null) {
            log.trace("StagiaireFacade.save id not null ");
            log.trace("stagiaire : " + stagiaire);
            return Optional.of(this.stagiaireRepo.save(stagiaire));
        } else {
            log.trace("StagiaireFacade.save id null ");
            return Optional.of(this.stagiaireRepo.save(stagiaire));
        }


////            Optional<Stagiaire> oStagiaire = this.stagiaireRepo.findStagiaireByNomAndPrenom(stagiaire.getNom(), stagiaire.getPrenom());
////            if (oStagiaire.isPresent()) {
//            Optional<Stagiaire> oStagiaire = this.stagiaireRepo.findStagiaireByNid(stagiaire.getNid());
//            if (oStagiaire.isPresent()) {
//                log.trace("stagaire findOne : " + this.stagiaireRepo.findOne(Example.of(stagiaire)));
//                return Optional.of(this.stagiaireRepo.save(stagiaire));
//            } else {
//                return Optional.of(this.stagiaireRepo.save(stagiaire));
//            }

//            }
//            return Optional.ofNullable(stagiaire);
//        } catch (DataIntegrityViolationException e) {
//            log.trace("StagiaireFacade.save exception : DataIntegrityViolationException ");
//            e.printStackTrace();
//            return Optional.ofNullable(stagiaire);
//        } catch (Exception e) {
//            log.trace("StagiaireFacade.save exception : Exception ");
//            e.printStackTrace();
//            return Optional.ofNullable(stagiaire);
//        }
    }

    @Override
    public void delete(Stagiaire stagiaire) {
        this.stagiaireRepo.delete(stagiaire);
    }

    @Override
    public Optional<Stagiaire> findById(Long id) {
        return this.stagiaireRepo.findById(id);
    }

    @Override
    public List<Stagiaire> findAll() {
        return super.stagiaireRepo.findAll();
    }

    @Override
    public Optional<Stagiaire> findByNomPrenom(String nom, String prenom) {
        return null;
    }

    @Override
    public boolean exist(Long id) {
        return super.stagiaireRepo.existsById(id);
    }


}
