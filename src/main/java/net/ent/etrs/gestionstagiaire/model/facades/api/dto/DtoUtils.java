package net.ent.etrs.gestionstagiaire.model.facades.api.dto;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import net.ent.etrs.gestionstagiaire.model.entities.Evaluation;
import net.ent.etrs.gestionstagiaire.model.entities.Formateur;
import net.ent.etrs.gestionstagiaire.model.entities.Note;
import net.ent.etrs.gestionstagiaire.model.entities.Stagiaire;
import net.ent.etrs.gestionstagiaire.model.facades.FacaceNote;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@CommonsLog(topic = "SOUT")
public final class DtoUtils {

    private static FacaceNote facaceNote;


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

    private static FormateurDto formateurToDto(Formateur formateur) {
        FormateurDto formateurDto = new FormateurDto();
        //TODO pas fini

        return formateurDto;
    }

    private static Formateur formateurFromDto(FormateurDto formateurDto) {
        Formateur formateur = new Formateur();
        //TODO pas fini

        return formateur;
    }

    private static EvaluationDto evaluationToDto(Evaluation evaluation) {
        EvaluationDto evaluationDto = new EvaluationDto();
        //TODO pas fini

        return evaluationDto;
    }

    private static Evaluation evaluationFromDto(EvaluationDto evaluationDto) {
        Evaluation evaluation = new Evaluation();
        //TODO pas fini

        return evaluation;
    }

}
