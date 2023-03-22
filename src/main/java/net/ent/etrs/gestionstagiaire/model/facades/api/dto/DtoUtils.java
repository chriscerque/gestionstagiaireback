package net.ent.etrs.gestionstagiaire.model.facades.api.dto;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import net.ent.etrs.gestionstagiaire.model.entities.*;
import net.ent.etrs.gestionstagiaire.model.facades.FacaceNote;
import net.ent.etrs.gestionstagiaire.model.facades.FacadeFormateur;
import net.ent.etrs.gestionstagiaire.model.facades.FacadeIngenierieFormation;
import net.ent.etrs.gestionstagiaire.model.facades.exceptions.BusinessException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;


@Service
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@CommonsLog(topic = "SOUT")
public final class DtoUtils {


    private static FacaceNote facaceNote;
    private static FacadeFormateur facadeFormateur;
    private static FacadeIngenierieFormation facadeIngenierieFormation;
    @Resource
    private FacaceNote vFacaceNote;
    @Resource
    private FacadeIngenierieFormation vFacadeIngenierieFormation;
    @Resource
    private FacadeFormateur vFacadeFormateur;

    public static StagiaireDto stagiaireToDto(Stagiaire stagiaire) {
        return StagiaireDto.builder()
                .id(stagiaire.getId())
                .nom(stagiaire.getNom())
                .prenom(stagiaire.getPrenom())
                .grade(stagiaire.getGrade())
                .appartenance(stagiaire.getAppartenance())
                .dateNaissance(stagiaire.getDateNaissance())
                .matricule(stagiaire.getMatricule())
                .nid(stagiaire.getNid())
                .numBatiment(stagiaire.getNumBatiment())
                .numChambre(stagiaire.getNumChambre())
                .numBadgeMess(stagiaire.getNumBadgeMess())
                .numBadgeAcces(stagiaire.getNumBadgeAcces())
                .build();

    }

//    static {
//        facaceNote = FacadeFactory.getFacadeNote();
//        facadeFormateur = FacadeFactory.getFacadeFormateur();
//        facadeIngenierieFormation = FacadeFactory.getFacaceIngenierieFormation();
//    }

    public static Stagiaire stagiaireFromDto(StagiaireDto stagiaireDto) {
        Stagiaire stagiaire = new Stagiaire();
        stagiaire.setId(stagiaireDto.getId());
        stagiaire.setNom(stagiaireDto.getNom());
        stagiaire.setPrenom(stagiaireDto.getPrenom());
        stagiaire.setGrade(stagiaireDto.getGrade());
        stagiaire.setAppartenance(stagiaireDto.getAppartenance());
        stagiaire.setDateNaissance(stagiaireDto.getDateNaissance());
        stagiaire.setMatricule(stagiaireDto.getMatricule());
        stagiaire.setNid(stagiaireDto.getNid());
        stagiaire.setNumBatiment(stagiaireDto.getNumBatiment());
        stagiaire.setNumChambre(stagiaireDto.getNumChambre());
        stagiaire.setNumBadgeMess(stagiaireDto.getNumBadgeMess());
        stagiaire.setNumBadgeAcces(stagiaireDto.getNumBadgeAcces());


        log.trace("stagiaireFromDto : " + stagiaireDto);
        return stagiaire;
    }

    public static StageDto stageToDto(Stage stage) {
        return StageDto.builder()
                .id(stage.getId())
                .codeStage(stage.getCodeStage())
                .dateDebut(stage.getDateDebut())
                .dateFin(stage.getDateFin())
                .cdsfId(stage.getCdsf() == null ? null : stage.getCdsf().getId())
                .ingenierieFormationId(stage.getIngenierieFormation() == null ? null : stage.getIngenierieFormation().getId())
                .build();
    }

    public static Stage stageFromDto(StageDto stageDto) throws BusinessException {
        Stage stage = new Stage();
        //TODO pas fini
        stage.setId(stageDto.getId());
        stage.setCodeStage(stageDto.getCodeStage());
        stage.setDateDebut(stageDto.getDateDebut());
        stage.setDateFin(stageDto.getDateFin());
        stage.setCdsf(facadeFormateur.findById(stageDto.getCdsfId()).orElseThrow(BusinessException::new));
        stage.setIngenierieFormation(facadeIngenierieFormation.findById(stageDto.getIngenierieFormationId()).orElseThrow(BusinessException::new));
        return stage;
    }

    public static NoteDto noteToDto(Note note) {
        NoteDto noteDto = new NoteDto();
        noteDto.setDateNote(note.getDateNote());
        noteDto.setEvaluationId(note.getEvaluation().getId());
        noteDto.setFormateurId(note.getFormateur().getId());
        return noteDto;
    }

    public static Note noteFromDto(NoteDto noteDto) {
        Note note = new Note();
        //TODO pas fini

        return note;
    }

    public static FormateurDto formateurToDto(Formateur formateur) {
        FormateurDto formateurDto = new FormateurDto();
        //TODO pas fini

        return formateurDto;
    }

    public static Formateur formateurFromDto(FormateurDto formateurDto) {
        Formateur formateur = new Formateur();
        //TODO pas fini

        return formateur;
    }

    public static EvaluationDto evaluationToDto(Evaluation evaluation) {
        EvaluationDto evaluationDto = new EvaluationDto();
        //TODO pas fini

        return evaluationDto;
    }

    public static Evaluation evaluationFromDto(EvaluationDto evaluationDto) {
        Evaluation evaluation = new Evaluation();
        //TODO pas fini

        return evaluation;
    }

    @PostConstruct
    public void init() {
        facadeFormateur = vFacadeFormateur;
        facaceNote = vFacaceNote;
        facadeIngenierieFormation = vFacadeIngenierieFormation;
    }

}
