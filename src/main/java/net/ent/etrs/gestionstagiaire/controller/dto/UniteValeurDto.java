package net.ent.etrs.gestionstagiaire.controller.dto;


import lombok.*;
import net.ent.etrs.gestionstagiaire.model.entities.AbstractEntity;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UniteValeurDto extends AbstractEntity {

    private Long id;

    private Integer indice;


}
