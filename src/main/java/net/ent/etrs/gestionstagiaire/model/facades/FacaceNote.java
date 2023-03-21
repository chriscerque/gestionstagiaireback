package net.ent.etrs.gestionstagiaire.model.facades;

import net.ent.etrs.gestionstagiaire.model.entities.Note;

import java.util.List;
import java.util.Optional;

public interface FacaceNote {

    Optional<Note> findById(Long id);

    List<Note> findAllById(List<Long> notes);
}
