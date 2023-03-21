package net.ent.etrs.gestionstagiaire.model.facades.api.dto;


import lombok.*;
import net.ent.etrs.gestionstagiaire.model.entities.AbstractEntity;
import net.ent.etrs.gestionstagiaire.model.entities.references.TypeIF;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class IngenierieFormationDto extends AbstractEntity {

    private Long id;

    private String libelle;

    private TypeIF typeIF;

    private LocalDate dateDebut;

    private LocalDate dateFin;


}
