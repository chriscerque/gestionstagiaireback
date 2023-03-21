package net.ent.etrs.gestionstagiaire.model.facades.impl;

import net.ent.etrs.gestionstagiaire.model.entities.references.Grade;
import net.ent.etrs.gestionstagiaire.model.facades.FacadeGrade;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class FacadeGradeImpl implements FacadeGrade {
    @Override
    public List<Grade> findAll() {
        return Arrays.asList(Grade.values());
    }
}
