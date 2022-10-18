package net.ent.etrs.gestionstagiaire.model.entities;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.ent.etrs.gestionstagiaire.model.entities.references.Appartenance;
import net.ent.etrs.gestionstagiaire.model.entities.references.Cmodel;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@NoArgsConstructor(access = AccessLevel.PUBLIC)

@Entity
@Table(name = "EVALUATION")
public class Evaluation extends AbstractEntity{

    @Getter
    @Setter
    @Min(value = 0, message = Cmodel.BV_EVALUATION_COEFF)
    @Column(name = "COEFF",nullable = false)
    private Integer coeff;

    @Getter @Setter
    @NotNull(message = Cmodel.BV_EVALUATION_TYPE_STAGIAIRE)
    @Valid
    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE_STAGIAIRE",nullable = false)
    private Appartenance typeStagiaire;

    @Getter @Setter
    @NotNull(message = Cmodel.BV_EVALUATION_MATIERE)
    @ManyToOne
    @JoinColumn(name = "MATIERE_ID", foreignKey = @ForeignKey(name = "FK_EVALUATION_MATIERE_ID"))
    private Matiere matiere;
}
