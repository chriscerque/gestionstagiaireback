package net.ent.etrs.gestionstagiaire.model.facades.api.dto;


import lombok.*;
import net.ent.etrs.gestionstagiaire.model.entities.references.Appartenance;
import net.ent.etrs.gestionstagiaire.model.entities.references.Grade;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StagiaireDto {

    private Long id;

    private String nom;

    private String prenom;

    private Grade grade;

    private Appartenance appartenance;

    private LocalDate dateNaissance;

    private String matricule;

    private String nid;

    private Integer numBatiment;

    private Integer numChambre;

    private Integer numBadgeMess;

    private Integer numBadgeAcces;


}
