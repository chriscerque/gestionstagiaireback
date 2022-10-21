package net.ent.etrs.gestionstagiaire.model.services;

import net.ent.etrs.gestionstagiaire.model.entities.Stagiaire;
import net.ent.etrs.gestionstagiaire.model.repo.FormateurRepo;
import net.ent.etrs.gestionstagiaire.model.repo.StageRepo;
import net.ent.etrs.gestionstagiaire.model.repo.StagiaireRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StagiaireFacade extends AbstractFacade implements IStagiaireFacade {


    protected StagiaireFacade(StageRepo stageRepo, StagiaireRepo stagiaireRepo, FormateurRepo formateurRepo) {
        super(stageRepo, stagiaireRepo, formateurRepo);
    }

    @Override
    public Optional<Stagiaire> save(Stagiaire stagiaire) {
        Stagiaire st = this.stagiaireRepo.findStagiaireByNomAndPrenom(stagiaire.getNom(), stagiaire.getPrenom());
        if (st == null) {
            return Optional.ofNullable(this.stagiaireRepo.save(stagiaire));
        }
        return Optional.ofNullable(st);
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
