package net.ent.etrs.gestionstagiaire.model.services.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import net.ent.etrs.gestionstagiaire.controller.dto.StageDto;
import net.ent.etrs.gestionstagiaire.model.entities.Stage;
import net.ent.etrs.gestionstagiaire.model.repo.StageRepo;
import net.ent.etrs.gestionstagiaire.model.services.ServiceFormateur;
import net.ent.etrs.gestionstagiaire.model.services.ServiceIngenierieFormation;
import net.ent.etrs.gestionstagiaire.model.services.ServiceStage;
import net.ent.etrs.gestionstagiaire.model.services.exceptions.BusinessException;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@CommonsLog(topic = "SOUT")
@RequiredArgsConstructor
public class ServiceStageImpl implements ServiceStage {
    @NonNull
    private StageRepo stageRepo;
    @NonNull
    private ServiceFormateur serviceFormateur;
    @NonNull
    private ServiceIngenierieFormation serviceIngenierieFormation;

    @Override
    public Optional<StageDto> save(StageDto stageDto) {
        try {
            log.trace("stage : " + stageDto);
            return Optional.of(this.toDto(this.stageRepo.save(this.fromDto(stageDto))));
        } catch (IllegalArgumentException | OptimisticLockingFailureException | BusinessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void delete(StageDto stageDto) throws BusinessException {

        try {
            this.stageRepo.delete(this.fromDto(stageDto));
        } catch (IllegalArgumentException | OptimisticLockingFailureException | BusinessException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<StageDto> findByCodeStage(String codeStage) throws BusinessException {
        return Optional.of(this.toDto(this.stageRepo.findByCodeStage(codeStage).orElseThrow(BusinessException::new)));
    }

    @Override
    public List<StageDto> findAll() {
        return this.stageRepo.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public Optional<StageDto> findById(Long id) throws BusinessException {
        return Optional.of(this.toDto(this.stageRepo.findById(id).orElseThrow(BusinessException::new)));
    }

    @Override
    public boolean exist(Long id) {
        return this.stageRepo.existsById(id);
    }

    @Override
    public Long count() {
        return this.stageRepo.count();
    }

    @Override
    public StageDto toDto(Stage stage) {
        StageDto stageDto = StageDto.builder().build();
        BeanUtils.copyProperties(stage, stageDto);
        stageDto.setCdsfId(stage.getCdsf() == null ? null : stage.getCdsf().getId());
        stageDto.setIngenierieFormationId(stage.getIngenierieFormation() == null ? null : stage.getIngenierieFormation().getId());
//        //TODO: sout
//        System.out.println("toDto / stage : " + stage);
//        System.out.println("toDto / stageDto : " + stageDto);
        return stageDto;


//        return StageDto.builder()
//                .id(stage.getId())
//                .codeStage(stage.getCodeStage())
//                .dateDebut(stage.getDateDebut())
//                .dateFin(stage.getDateFin())
//                .cdsfId(stage.getCdsf() == null ? null : stage.getCdsf().getId())
//                .ingenierieFormationId(stage.getIngenierieFormation() == null ? null : stage.getIngenierieFormation().getId())
//                .build();
    }

    @Override
    public Stage fromDto(StageDto stageDto) throws BusinessException {
        Stage stage = new Stage();
        BeanUtils.copyProperties(stageDto, stage);
//        //TODO: sout
//        System.out.println("fromDto / stage : " + stage);
        stage.setCdsf(serviceFormateur.findById(stageDto.getCdsfId()).orElseThrow(BusinessException::new));
        stage.setIngenierieFormation(serviceIngenierieFormation.findById(stageDto.getIngenierieFormationId()).orElseThrow(BusinessException::new));
        return stage;
    }
}
