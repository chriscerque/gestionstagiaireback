package net.ent.etrs.gestionstagiaire.model.facades;

import net.ent.etrs.gestionstagiaire.model.entities.Formateur;
import org.springframework.dao.DataIntegrityViolationException;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

public interface FacadeFormateur {

    Optional<Formateur> save(Formateur formateur) throws DataIntegrityViolationException, ConstraintViolationException;

    void delete(Formateur formateur);

    Optional<Formateur> findById(Long id);

    List<Formateur> findAll();

    Optional<Formateur> findByNomPrenom(String nom, String prenom);


    boolean exist(Long id);
}
