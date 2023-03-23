package net.ent.etrs.gestionstagiaire.model.services;

import net.ent.etrs.gestionstagiaire.model.entities.Note;

import java.util.List;
import java.util.Optional;

public interface ServiceNote {

    Optional<Note> findById(Long id);

    List<Note> findAllById(List<Long> notes);
}
