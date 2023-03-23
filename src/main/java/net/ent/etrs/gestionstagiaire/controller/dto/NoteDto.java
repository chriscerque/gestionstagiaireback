package net.ent.etrs.gestionstagiaire.controller.dto;


import lombok.*;
import net.ent.etrs.gestionstagiaire.model.entities.AbstractEntity;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NoteDto extends AbstractEntity {

    private Long id;

    private Long evaluationId;

    private Double valeur;

    private Long formateurId;

    private LocalDate dateNote;

}
