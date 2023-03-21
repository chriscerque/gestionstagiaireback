package net.ent.etrs.gestionstagiaire.model.facades.api.dto;

import lombok.*;
import net.ent.etrs.gestionstagiaire.model.entities.AbstractEntity;
import net.ent.etrs.gestionstagiaire.model.entities.references.Appartenance;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EvaluationDto extends AbstractEntity {

    private Long id;

    private Integer coeff;

    private Appartenance typeStagiaire;

    private Long matiereId;
}
