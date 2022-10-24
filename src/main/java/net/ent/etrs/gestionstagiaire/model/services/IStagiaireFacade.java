package net.ent.etrs.gestionstagiaire.model.services;

import net.ent.etrs.gestionstagiaire.model.entities.Stagiaire;
import org.springframework.dao.DataIntegrityViolationException;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

public interface IStagiaireFacade {

    Optional<Stagiaire> save(Stagiaire stagiaire) throws DataIntegrityViolationException, ConstraintViolationException;

    void delete(Stagiaire stagiaire);

    Optional<Stagiaire> findById(Long id);

    List<Stagiaire> findAll();

    Optional<Stagiaire> findByNomPrenom(String nom, String prenom);


    boolean exist(Long id);
}
