package net.ent.etrs.gestionstagiaire.model.repo;


import net.ent.etrs.gestionstagiaire.model.entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface NoteRepo extends JpaRepository<Note, Long> {


}
