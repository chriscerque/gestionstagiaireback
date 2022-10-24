package net.ent.etrs.gestionstagiaire.model.services;

import net.ent.etrs.gestionstagiaire.model.entities.Stagiaire;
import net.ent.etrs.gestionstagiaire.model.repo.FormateurRepo;
import net.ent.etrs.gestionstagiaire.model.repo.StageRepo;
import net.ent.etrs.gestionstagiaire.model.repo.StagiaireRepo;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

@Service
public class StagiaireFacade extends AbstractFacade implements IStagiaireFacade {


    protected StagiaireFacade(StageRepo stageRepo, StagiaireRepo stagiaireRepo, FormateurRepo formateurRepo) {
        super(stageRepo, stagiaireRepo, formateurRepo);
    }

    @Override
    public Optional<Stagiaire> save(Stagiaire stagiaire) throws DataIntegrityViolationException, ConstraintViolationException {
//        try {

        if (stagiaire.getId() != null) {
            System.out.println("StagiaireFacade.save id not null ");
            System.out.println("stagiaire : " + stagiaire);
            return Optional.of(this.stagiaireRepo.save(stagiaire));
        } else {
            System.out.println("StagiaireFacade.save id null ");
            return Optional.of(this.stagiaireRepo.save(stagiaire));
        }


////            Optional<Stagiaire> oStagiaire = this.stagiaireRepo.findStagiaireByNomAndPrenom(stagiaire.getNom(), stagiaire.getPrenom());
////            if (oStagiaire.isPresent()) {
//            Optional<Stagiaire> oStagiaire = this.stagiaireRepo.findStagiaireByNid(stagiaire.getNid());
//            if (oStagiaire.isPresent()) {
//                System.out.println("stagaire findOne : " + this.stagiaireRepo.findOne(Example.of(stagiaire)));
//                return Optional.of(this.stagiaireRepo.save(stagiaire));
//            } else {
//                return Optional.of(this.stagiaireRepo.save(stagiaire));
//            }

//            }
//            return Optional.ofNullable(stagiaire);
//        } catch (DataIntegrityViolationException e) {
//            System.out.println("StagiaireFacade.save exception : DataIntegrityViolationException ");
//            e.printStackTrace();
//            return Optional.ofNullable(stagiaire);
//        } catch (Exception e) {
//            System.out.println("StagiaireFacade.save exception : Exception ");
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
