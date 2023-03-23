package net.ent.etrs.gestionstagiaire.controller.dto;


import lombok.*;
import net.ent.etrs.gestionstagiaire.model.entities.AbstractEntity;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "stagiaireList")
public class StageDto extends AbstractEntity {

    private Long id;

    private String codeStage;

    private LocalDate dateDebut;

    private LocalDate dateFin;

    private Long cdsfId;

    private Long ingenierieFormationId;


}
