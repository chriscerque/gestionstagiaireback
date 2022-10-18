package net.ent.etrs.gestionstagiaire.model.entities;


import lombok.Getter;
import lombok.Setter;
import net.ent.etrs.gestionstagiaire.model.entities.references.Cmodel;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

@Entity
@Table(name = "NOTE")
public class Note extends AbstractEntity{

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "EVALUATION", nullable = false, foreignKey = @ForeignKey(name = "FK_NOTE_EVALUATION_ID"))
    @NotNull(message = Cmodel.BV_NOTE_EVALUATION_NOT_NULL)
    @Valid
    private Evaluation evaluation;

    @Getter @Setter
    @PositiveOrZero(message = Cmodel.BV_NOTE_VALEUR_POSITIVE_OR_ZERO)
    @Column(name = "VALEUR")
    private Double valeur;

    @Getter @Setter
    @OneToOne()
    @JoinColumn(name = "FORMATEUR_ID", nullable = false, foreignKey = @ForeignKey(name = "FK_NOTE_FORMATEUR_ID"))
    @NotNull(message = Cmodel.BV_NOTE_FORMATEUR_NOT_NULL)
    @Valid
    private Formateur formateur;

    @Getter @Setter
//    @Convert(converter = LocalDateConverter.class)
    @Column(name = "DATE_NOTE", nullable = false)
    @NotNull(message = Cmodel.BV_NOTE_DATE_NOT_NULL)
    private LocalDate dateNote;

}
