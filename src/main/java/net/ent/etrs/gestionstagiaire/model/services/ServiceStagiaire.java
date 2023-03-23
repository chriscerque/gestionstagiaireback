package net.ent.etrs.gestionstagiaire.model.services;

import net.ent.etrs.gestionstagiaire.controller.dto.StagiaireDto;
import net.ent.etrs.gestionstagiaire.model.entities.Stagiaire;
import net.ent.etrs.gestionstagiaire.model.services.exceptions.BusinessException;
import org.springframework.dao.DataIntegrityViolationException;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

public interface ServiceStagiaire {

    Optional<StagiaireDto> save(StagiaireDto stagiaireDto) throws DataIntegrityViolationException, ConstraintViolationException;

    void delete(StagiaireDto stagiaireDto) throws BusinessException;

    Optional<StagiaireDto> findById(Long id) throws BusinessException;

    List<StagiaireDto> findAll();

    Optional<StagiaireDto> findByNomPrenom(String nom, String prenom) throws BusinessException;


    boolean exist(Long id);

    Long count();

    Optional<StagiaireDto> findStagiaireByNid(String s) throws BusinessException;

    Stagiaire fromDto(StagiaireDto stagiaireDto);

    StagiaireDto toDto(Stagiaire stagiaire);
}
