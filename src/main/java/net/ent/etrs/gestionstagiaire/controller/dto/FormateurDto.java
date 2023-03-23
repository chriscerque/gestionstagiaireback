package net.ent.etrs.gestionstagiaire.controller.dto;


import lombok.*;
import net.ent.etrs.gestionstagiaire.model.entities.AbstractEntity;
import net.ent.etrs.gestionstagiaire.model.entities.references.Grade;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FormateurDto extends AbstractEntity {

    private Long id;

    private String nom;

    private String prenom;

    private Grade grade;

}
