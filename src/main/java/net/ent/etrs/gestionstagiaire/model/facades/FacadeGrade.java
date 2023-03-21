package net.ent.etrs.gestionstagiaire.model.facades;

import net.ent.etrs.gestionstagiaire.model.entities.references.Grade;

import java.util.List;


public interface FacadeGrade {
    List<Grade> findAll();
}
