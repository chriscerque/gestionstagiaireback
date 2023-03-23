package net.ent.etrs.gestionstagiaire.model.services.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import net.ent.etrs.gestionstagiaire.model.entities.Note;
import net.ent.etrs.gestionstagiaire.model.repo.NoteRepo;
import net.ent.etrs.gestionstagiaire.model.services.ServiceNote;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@CommonsLog(topic = "SOUT")
@RequiredArgsConstructor
public class ServiceNoteImpl implements ServiceNote {
    @NonNull
    private NoteRepo noteRepo;


    @Override
    public Optional<Note> findById(Long id) {
        return this.noteRepo.findById(id);
    }

    @Override
    public List<Note> findAllById(List<Long> notes) {
        return this.noteRepo.findAllById(notes);
    }
}
