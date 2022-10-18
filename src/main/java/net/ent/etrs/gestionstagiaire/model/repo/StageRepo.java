package net.ent.etrs.gestionstagiaire.model.repo;


import net.ent.etrs.gestionstagiaire.model.entities.Stage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface StageRepo extends JpaRepository<Stage,Long> {

    Stage findByCodeStage(String codeStage);

}
