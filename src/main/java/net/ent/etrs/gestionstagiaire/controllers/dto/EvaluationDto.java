package net.ent.etrs.gestionstagiaire.controllers.dto;

import lombok.ToString;
import net.ent.etrs.gestionstagiaire.model.entities.AbstractEntity;
import net.ent.etrs.gestionstagiaire.model.entities.references.Appartenance;

@ToString
public class EvaluationDto extends AbstractEntity {

    public Integer coeff;

    public Appartenance typeStagiaire;

    public MatiereDto matiere;
}
