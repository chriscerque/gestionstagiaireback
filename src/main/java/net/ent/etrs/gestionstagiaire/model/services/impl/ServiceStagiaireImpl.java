package net.ent.etrs.gestionstagiaire.model.services.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import net.ent.etrs.gestionstagiaire.controller.dto.StagiaireDto;
import net.ent.etrs.gestionstagiaire.model.entities.Stagiaire;
import net.ent.etrs.gestionstagiaire.model.repo.StagiaireRepo;
import net.ent.etrs.gestionstagiaire.model.services.ServiceStagiaire;
import net.ent.etrs.gestionstagiaire.model.services.exceptions.BusinessException;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@CommonsLog(topic = "SOUT")
@RequiredArgsConstructor
public class ServiceStagiaireImpl implements ServiceStagiaire {
    @NonNull
    private StagiaireRepo stagiaireRepo;

    public Stagiaire fromDto(StagiaireDto stagiaireDto) {
        Stagiaire stagiaire = new Stagiaire();
        BeanUtils.copyProperties(stagiaireDto, stagiaire);

        log.trace("stagiaireFromDto : " + stagiaireDto);
        return stagiaire;
    }

    @Override
    public Optional<StagiaireDto> save(StagiaireDto stagiaireDto) throws DataIntegrityViolationException, ConstraintViolationException {
        try {
            log.trace("stagiaire : " + stagiaireDto);
            return Optional.of(this.toDto(this.stagiaireRepo.save(this.fromDto(stagiaireDto))));
        } catch (IllegalArgumentException | OptimisticLockingFailureException e) {
            return Optional.empty();
        }

    }

    @Override
    public void delete(StagiaireDto stagiaireDto) throws BusinessException {
        try {
            this.stagiaireRepo.delete(this.fromDto(stagiaireDto));
        } catch (IllegalArgumentException | OptimisticLockingFailureException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<StagiaireDto> findById(Long id) throws BusinessException {
        return Optional.of(this.toDto(this.stagiaireRepo.findById(id).orElseThrow(BusinessException::new)));
    }

    @Override
    public List<StagiaireDto> findAll() {
        return this.stagiaireRepo.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public Optional<StagiaireDto> findByNomPrenom(String nom, String prenom) throws BusinessException {
        return Optional.of(this.toDto(this.stagiaireRepo.findStagiaireByNomAndPrenom(nom, prenom).orElseThrow(BusinessException::new)));
    }

    @Override
    public boolean exist(Long id) {
        return this.stagiaireRepo.existsById(id);
    }

    @Override
    public Long count() {
        return this.stagiaireRepo.count();
    }

    @Override
    public Optional<StagiaireDto> findStagiaireByNid(String nid) throws BusinessException {
        //TODO
        return Optional.of(this.toDto(this.stagiaireRepo.findStagiaireByNid(nid).orElseThrow(BusinessException::new)));
    }

    public StagiaireDto toDto(Stagiaire stagiaire) {
        StagiaireDto stagiaireDto = new StagiaireDto();
        BeanUtils.copyProperties(stagiaire, stagiaireDto);
        return stagiaireDto;

//        return StagiaireDto.builder()
//                .id(stagiaire.getId())
//                .nom(stagiaire.getNom())
//                .prenom(stagiaire.getPrenom())
//                .grade(stagiaire.getGrade())
//                .appartenance(stagiaire.getAppartenance())
//                .dateNaissance(stagiaire.getDateNaissance())
//                .matricule(stagiaire.getMatricule())
//                .nid(stagiaire.getNid())
//                .numBatiment(stagiaire.getNumBatiment())
//                .numChambre(stagiaire.getNumChambre())
//                .numBadgeMess(stagiaire.getNumBadgeMess())
//                .numBadgeAcces(stagiaire.getNumBadgeAcces())
//                .build();

    }

}
