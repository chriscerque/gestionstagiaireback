package net.ent.etrs.gestionstagiaire.model.services.impl;

import lombok.extern.apachecommons.CommonsLog;
import net.ent.etrs.gestionstagiaire.model.entities.references.Grade;
import net.ent.etrs.gestionstagiaire.model.services.ServiceGrade;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@CommonsLog(topic = "SOUT")
public class ServiceGradeImpl implements ServiceGrade {
    @Override
    public List<Grade> findAll() {
        return Arrays.asList(Grade.values());
    }
}
