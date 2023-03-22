package net.ent.etrs.gestionstagiaire.model.entities;

import lombok.Getter;
import lombok.Setter;
import net.ent.etrs.gestionstagiaire.model.entities.references.Cmodel;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.Set;

@Entity
@Table(name = "UNITE_VALEUR")
public class UniteValeur extends AbstractEntity {

    @OneToMany
    @JoinColumn(name = "UV_ID", foreignKey = @ForeignKey(name = "FK__EVALUATION__UV_ID"))
//    @JoinTable(
//            name = "UV_EVALUATION",
//            joinColumns = @JoinColumn(name = "UV_ID"),
//            foreignKey = @ForeignKey(name = "FK_UV_EVALUATION_UV_ID"),
//            inverseJoinColumns = @JoinColumn(name = "EVALUATION_ID"),
//            inverseForeignKey = @ForeignKey(name = "FK_UV_EVALUATION_EVALUATION_ID")
//    )
    Set<Evaluation> evaluationList;
    @Getter
    @Setter
    @NotNull(message = Cmodel.BV_UNITE_VALEUR_INDICE_NOT_NULL)
    @PositiveOrZero(message = Cmodel.BV_UNITE_VALEUR_INDICE_POSITIVE_OR_ZERO)
    private Integer indice;

    public void ajouterEvaluation(@Valid Evaluation evaluation) {
        this.evaluationList.add(evaluation);
    }

    public void supprimerEvaluation(@Valid Evaluation evaluation) {
        this.evaluationList.remove(evaluation);
    }


}
