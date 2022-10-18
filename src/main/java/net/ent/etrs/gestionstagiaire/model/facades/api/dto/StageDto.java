package net.ent.etrs.gestionstagiaire.model.facades.api.dto;


import lombok.ToString;
import net.ent.etrs.gestionstagiaire.model.entities.AbstractEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ToString(exclude = "stagiaireList")
public class StageDto extends AbstractEntity {

    public IngenierieFormationDto ingenierieFormation;

    public String codeStage;

    public LocalDate dateDebut;

    public LocalDate dateFin;

    public FormateurDto cdsf;


    private List<StagiaireDto> stagiaireList = new ArrayList<>();

    public void addStagiaire(StagiaireDto stagiaire){
        this.stagiaireList.add(stagiaire);
    }

    public void supprStagiaire(StagiaireDto stagiaire){
        this.stagiaireList.remove(stagiaire);
    }

}
