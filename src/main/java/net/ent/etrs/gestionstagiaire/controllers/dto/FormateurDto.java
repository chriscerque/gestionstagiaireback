package net.ent.etrs.gestionstagiaire.controllers.dto;


import lombok.ToString;
import net.ent.etrs.gestionstagiaire.model.entities.AbstractEntity;
import net.ent.etrs.gestionstagiaire.model.entities.references.Grade;


@ToString
public class FormateurDto extends AbstractEntity {

    public String nom;

    public String prenom;

    public Grade grade;

}
