package net.ent.etrs.gestionstagiaire.model.facades;

import net.ent.etrs.gestionstagiaire.model.entities.Stage;

import java.util.List;
import java.util.Optional;


public interface FacadeStage {

    Optional<Stage> save(Stage stage);

    void delete(Stage stage);

    Optional<Stage> findByCodeStage(Long id);

    List<Stage> findAll();

    Optional<Stage> findById(Long id);

    Optional<Stage> findByCodeStage(String codeStage);

    boolean exist(Long id);

    Long count();
}
