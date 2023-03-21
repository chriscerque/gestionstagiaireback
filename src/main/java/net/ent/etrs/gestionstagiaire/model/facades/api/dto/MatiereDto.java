package net.ent.etrs.gestionstagiaire.model.facades.api.dto;


import lombok.*;
import net.ent.etrs.gestionstagiaire.model.entities.AbstractEntity;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MatiereDto extends AbstractEntity {

    private Long id;

    private String nom;

}
