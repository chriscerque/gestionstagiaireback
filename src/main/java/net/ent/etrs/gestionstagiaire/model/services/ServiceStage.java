package net.ent.etrs.gestionstagiaire.model.services;

import net.ent.etrs.gestionstagiaire.controller.dto.StageDto;
import net.ent.etrs.gestionstagiaire.model.entities.Stage;
import net.ent.etrs.gestionstagiaire.model.services.exceptions.BusinessException;

import java.util.List;
import java.util.Optional;


public interface ServiceStage {

    Optional<StageDto> save(StageDto stageDto);

    void delete(StageDto stageDto) throws BusinessException;

    List<StageDto> findAll();

    Optional<StageDto> findById(Long id) throws BusinessException;

    Optional<StageDto> findByCodeStage(String codeStage) throws BusinessException;

    boolean exist(Long id);

    Long count();

    StageDto toDto(Stage stage);

    Stage fromDto(StageDto stageDto) throws BusinessException;
}
