package net.ent.etrs.gestionstagiaire.model.services.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import net.ent.etrs.gestionstagiaire.model.entities.IngenierieFormation;
import net.ent.etrs.gestionstagiaire.model.repo.IngenierieFormationRepo;
import net.ent.etrs.gestionstagiaire.model.services.ServiceIngenierieFormation;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

@Service
@CommonsLog(topic = "SOUT")
@RequiredArgsConstructor
public class ServiceIngenierieFormationImpl implements ServiceIngenierieFormation {
    @NonNull
    private IngenierieFormationRepo ingenierieFormationRepo;


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
        return this.ingenierieFormationRepo.findAll();
    }

    @Override
    public Optional<IngenierieFormation> findByLibelle(String libelle) {
        return null;
    }

    @Override
    public boolean exist(Long id) {
        return this.ingenierieFormationRepo.existsById(id);
    }

    @Override
    public Long count() {
        return this.ingenierieFormationRepo.count();
    }


}
