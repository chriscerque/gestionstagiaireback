package net.ent.etrs.gestionstagiaire.model.services;

import net.ent.etrs.gestionstagiaire.model.entities.IngenierieFormation;
import org.springframework.dao.DataIntegrityViolationException;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

public interface ServiceIngenierieFormation {

    Optional<IngenierieFormation> save(IngenierieFormation ingenierieFormation) throws DataIntegrityViolationException, ConstraintViolationException;

    void delete(IngenierieFormation ingenierieFormation);

    Optional<IngenierieFormation> findById(Long id);

    List<IngenierieFormation> findAll();

    Optional<IngenierieFormation> findByLibelle(String libelle);


    boolean exist(Long id);

    Long count();
}
