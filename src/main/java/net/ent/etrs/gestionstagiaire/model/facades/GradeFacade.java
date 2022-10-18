package net.ent.etrs.gestionstagiaire.model.facades;

import net.ent.etrs.gestionstagiaire.model.entities.references.Grade;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class GradeFacade implements IGradeFacade{
    @Override
    public List<Grade> readAllGrades() {
        return Arrays.asList(Grade.values());
    }
}
