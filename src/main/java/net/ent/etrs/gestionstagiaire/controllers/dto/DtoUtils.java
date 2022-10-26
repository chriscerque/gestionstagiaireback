package net.ent.etrs.gestionstagiaire.controllers.dto;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.ent.etrs.gestionstagiaire.model.entities.Evaluation;
import net.ent.etrs.gestionstagiaire.model.entities.Formateur;
import net.ent.etrs.gestionstagiaire.model.entities.Note;
import net.ent.etrs.gestionstagiaire.model.entities.Stagiaire;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DtoUtils {

    public static StagiaireDto stagiaireToDto(Stagiaire stagiaire) {
        StagiaireDto stagiaireDto = new StagiaireDto();
        stagiaireDto.nom = stagiaire.getNom();
        stagiaireDto.prenom = stagiaire.getPrenom();
        stagiaireDto.grade = stagiaire.getGrade();
        stagiaireDto.appartenance = stagiaire.getAppartenance();
        stagiaireDto.dateNaissance = stagiaire.getDateNaissance();
        stagiaireDto.matricule = stagiaire.getMatricule();
        stagiaireDto.nid = stagiaire.getNid();
        stagiaireDto.numBatiment = stagiaire.getNumBatiment();
        stagiaireDto.numChambre = stagiaire.getNumChambre();
        stagiaireDto.numBadgeMess = stagiaire.getNumBadgeMess();
        stagiaireDto.numBadgeAcces = stagiaire.getNumBadgeAcces();
        stagiaire.getNoteList().forEach(n -> stagiaireDto.ajouterNote(DtoUtils.noteToDto(n)));

        return stagiaireDto;
    }

    public static Stagiaire stagiaireFromDto(StagiaireDto stagiaireDto) {
        Stagiaire stagiaire = new Stagiaire();
        stagiaire.setId(stagiaireDto.getId());
        stagiaire.setNom(stagiaireDto.nom);
        stagiaire.setPrenom(stagiaireDto.prenom);
        stagiaire.setGrade(stagiaireDto.grade);
        stagiaire.setAppartenance(stagiaireDto.appartenance);
        stagiaire.setDateNaissance(stagiaireDto.dateNaissance);
        stagiaire.setMatricule(stagiaireDto.matricule);
        stagiaire.setNid(stagiaireDto.nid);
        stagiaire.setNumBatiment(stagiaireDto.numBatiment);
        stagiaire.setNumChambre(stagiaireDto.numChambre);
        stagiaire.setNumBadgeMess(stagiaireDto.numBadgeMess);
        stagiaire.setNumBadgeAcces(stagiaireDto.numBadgeAcces);
        stagiaireDto.noteList.forEach(n -> stagiaire.ajouterNote(DtoUtils.noteFromDto(n)));
        System.out.println("stagiaireFromDto : " + stagiaireDto);
        return stagiaire;
    }

    public static NoteDto noteToDto(Note note) {
        NoteDto noteDto = new NoteDto();
        noteDto.dateNote = note.getDateNote();
        noteDto.evaluation = DtoUtils.evaluationToDto(note.getEvaluation());
        noteDto.formateur = DtoUtils.formateurToDto(note.getFormateur());
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
