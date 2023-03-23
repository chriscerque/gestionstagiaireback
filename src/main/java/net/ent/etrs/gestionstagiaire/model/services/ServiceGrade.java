package net.ent.etrs.gestionstagiaire.model.services;

import net.ent.etrs.gestionstagiaire.model.entities.references.Grade;

import java.util.List;


public interface ServiceGrade {
    List<Grade> findAll();
}
