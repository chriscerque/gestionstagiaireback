package net.ent.etrs.gestionstagiaire.controller.dto;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import net.ent.etrs.gestionstagiaire.model.entities.Evaluation;
import net.ent.etrs.gestionstagiaire.model.entities.Formateur;
import net.ent.etrs.gestionstagiaire.model.entities.Note;
import net.ent.etrs.gestionstagiaire.model.entities.Stage;
import net.ent.etrs.gestionstagiaire.model.services.ServiceFormateur;
import net.ent.etrs.gestionstagiaire.model.services.ServiceIngenierieFormation;
import net.ent.etrs.gestionstagiaire.model.services.ServiceNote;
import net.ent.etrs.gestionstagiaire.model.services.exceptions.BusinessException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;


@Service
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@CommonsLog(topic = "SOUT")
public final class DtoUtils {


    private static ServiceNote serviceNote;
    private static ServiceFormateur serviceFormateur;
    private static ServiceIngenierieFormation serviceIngenierieFormation;
    @Resource
    private ServiceNote vServiceNote;
    @Resource
    private ServiceIngenierieFormation vServiceIngenierieFormation;
    @Resource
    private ServiceFormateur vServiceFormateur;


//    static {
//        facaceNote = FacadeFactory.getFacadeNote();
//        facadeFormateur = FacadeFactory.getFacadeFormateur();
//        facadeIngenierieFormation = FacadeFactory.getFacaceIngenierieFormation();
//    }


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
        stage.setCdsf(serviceFormateur.findById(stageDto.getCdsfId()).orElseThrow(BusinessException::new));
        stage.setIngenierieFormation(serviceIngenierieFormation.findById(stageDto.getIngenierieFormationId()).orElseThrow(BusinessException::new));
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
        serviceFormateur = vServiceFormateur;
        serviceNote = vServiceNote;
        serviceIngenierieFormation = vServiceIngenierieFormation;
    }

}
