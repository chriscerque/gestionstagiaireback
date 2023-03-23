package net.ent.etrs.gestionstagiaire.model.services.impl;

import lombok.extern.apachecommons.CommonsLog;
import net.ent.etrs.gestionstagiaire.model.entities.Formateur;
import net.ent.etrs.gestionstagiaire.model.services.ServiceFormateur;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

@Component
@CommonsLog(topic = "SOUT")
public class ServiceFormateurImpl extends AbstractService implements ServiceFormateur {


//    protected FacadeFormateurImpl(StageRepo stageRepo, StagiaireRepo stagiaireRepo, FormateurRepo formateurRepo, EvaluationRepo evaluationRepo, IngenierieFormationRepo ingenierieFormationRepo, MatiereRepo matiereRepo, NoteRepo noteRepo, UniteValeurRepo uniteValeurRepo) {
//        super(stageRepo, stagiaireRepo, formateurRepo, evaluationRepo, ingenierieFormationRepo, matiereRepo, noteRepo, uniteValeurRepo);
//    }


    @Override
    public Optional<Formateur> save(Formateur formateur) throws DataIntegrityViolationException, ConstraintViolationException {
//        try {

        if (formateur.getId() != null) {
            log.trace("FormateurFacade.save id not null ");
            log.trace("formateur : " + formateur);
            return Optional.of(this.formateurRepo.save(formateur));
        } else {
            log.trace("FormateurFacade.save id null ");
            return Optional.of(this.formateurRepo.save(formateur));
        }


////            Optional<Formateur> oFormateur = this.formateurRepo.findFormateurByNomAndPrenom(formateur.getNom(), formateur.getPrenom());
////            if (oFormateur.isPresent()) {
//            Optional<Formateur> oFormateur = this.formateurRepo.findFormateurByNid(formateur.getNid());
//            if (oFormateur.isPresent()) {
//                log.trace("stagaire findOne : " + this.formateurRepo.findOne(Example.of(formateur)));
//                return Optional.of(this.formateurRepo.save(formateur));
//            } else {
//                return Optional.of(this.formateurRepo.save(formateur));
//            }

//            }
//            return Optional.ofNullable(formateur);
//        } catch (DataIntegrityViolationException e) {
//            log.trace("FormateurFacade.save exception : DataIntegrityViolationException ");
//            e.printStackTrace();
//            return Optional.ofNullable(formateur);
//        } catch (Exception e) {
//            log.trace("FormateurFacade.save exception : Exception ");
//            e.printStackTrace();
//            return Optional.ofNullable(formateur);
//        }
    }

    @Override
    public void delete(Formateur formateur) {
        this.formateurRepo.delete(formateur);
    }

    @Override
    public Optional<Formateur> findById(Long id) {
        //TODO sout
        System.out.println("id : " + id);
        System.out.println("this.formateurRepo.findById(id) : " + this.formateurRepo.findById(id));
        return this.formateurRepo.findById(id);
    }

    @Override
    public List<Formateur> findAll() {
        return super.formateurRepo.findAll();
    }

    @Override
    public Optional<Formateur> findByNomPrenom(String nom, String prenom) {
        return null;
    }

    @Override
    public boolean exist(Long id) {
        return super.formateurRepo.existsById(id);
    }


}
