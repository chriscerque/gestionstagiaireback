package net.ent.etrs.gestionstagiaire.model.facades.api.dto;


import lombok.ToString;
import net.ent.etrs.gestionstagiaire.model.entities.AbstractEntity;

import java.util.HashSet;
import java.util.Set;

@ToString
public class UniteValeurDto extends AbstractEntity {

    private Integer indice;


    Set<EvaluationDto> evaluationList = new HashSet<>();

    public void ajouterEvaluation(EvaluationDto evaluation){
        this.evaluationList.add(evaluation);
    }

    public void supprimerEvaluation(EvaluationDto evaluation){
        this.evaluationList.remove(evaluation);
    }


}
