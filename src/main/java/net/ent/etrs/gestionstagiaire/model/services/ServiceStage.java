package net.ent.etrs.gestionstagiaire.model.services;

import net.ent.etrs.gestionstagiaire.model.entities.Stage;

import java.util.List;
import java.util.Optional;


public interface ServiceStage {

    Optional<Stage> save(Stage stage);

    void delete(Stage stage);

    Optional<Stage> findByCodeStage(Long id);

    List<Stage> findAll();

    Optional<Stage> findById(Long id);

    Optional<Stage> findByCodeStage(String codeStage);

    boolean exist(Long id);

    Long count();
}
