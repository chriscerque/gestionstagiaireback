package net.ent.etrs.gestionstagiaire.model.facades.api.dto;


import lombok.ToString;
import net.ent.etrs.gestionstagiaire.model.entities.AbstractEntity;

import java.time.LocalDate;

@ToString
public class NoteDto extends AbstractEntity {

    public EvaluationDto evaluation;

    public Double valeur;

    public FormateurDto formateur;

    public LocalDate dateNote;

}
