package net.ent.etrs.gestionstagiaire;


import lombok.extern.apachecommons.CommonsLog;
import net.ent.etrs.gestionstagiaire.controllers.dto.DtoUtils;
import net.ent.etrs.gestionstagiaire.controllers.dto.StagiaireDto;
import net.ent.etrs.gestionstagiaire.model.entities.Formateur;
import net.ent.etrs.gestionstagiaire.model.entities.MyUserDetails;
import net.ent.etrs.gestionstagiaire.model.entities.Stage;
import net.ent.etrs.gestionstagiaire.model.entities.Stagiaire;
import net.ent.etrs.gestionstagiaire.model.entities.references.Appartenance;
import net.ent.etrs.gestionstagiaire.model.entities.references.Grade;
import net.ent.etrs.gestionstagiaire.model.repo.FormateurRepo;
import net.ent.etrs.gestionstagiaire.model.repo.StagiaireRepo;
import net.ent.etrs.gestionstagiaire.model.repo.UserRepo;
import net.ent.etrs.gestionstagiaire.model.services.IStageFacade;
import net.ent.etrs.gestionstagiaire.security.services.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@CommonsLog(topic = "SOUT")
public class MyRunner2 implements CommandLineRunner {

    //    @Autowired
//    public StageRepo stageRepo;
    @Autowired
    public IStageFacade iStageFacade;
    @Autowired
    public FormateurRepo formateurRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private StagiaireRepo stagiaireRepo;
    @Autowired
    private MyUserDetailService userDetailsService;

    @Autowired
    private PasswordEncoder encoder;


    @Value("${toto.var}")
    private String text;

//    @Autowired
//    public UserRepo userRepo;

    @Override
    public void run(String... args) throws Exception {
        log.trace(this.text);


        try {
            log.trace(">>>>>>>>>>>>>>>>>>>runner 0");
            userRepo.save(MyUserDetails.builder().username("ADMIN").password(encoder.encode("ADMIN")).accountNonExpired(true).credentialsNonExpired(true).accountNonLocked(true).enabled(true).build());
            userRepo.save(MyUserDetails.builder().username("ADMIN2").password(encoder.encode("ADMIN2")).enabled(true).build());
            userRepo.save(MyUserDetails.builder().username("ADMIN3").password(encoder.encode("ADMIN3")).accountNonExpired(true).credentialsNonExpired(true).accountNonLocked(true).enabled(true).build());
            userRepo.save(MyUserDetails.builder().username("ADMIN4").password(encoder.encode("ADMIN4")).accountNonExpired(false).accountNonLocked(true).credentialsNonExpired(false).enabled(true).build());


            StagiaireDto stagiaireDto = new StagiaireDto();
            stagiaireDto.nid = "0123456789";
            stagiaireDto.nom = "0123456789";
            stagiaireDto.prenom = "0123456789";
            stagiaireDto.dateNaissance = LocalDate.now().minusYears(18);
            stagiaireDto.matricule = "0337010562";
            stagiaireDto.grade = Grade.SCH;
            stagiaireDto.appartenance = Appartenance.AIR;
            stagiaireRepo.save(DtoUtils.stagiaireFromDto(stagiaireDto));


            log.trace(">>>>>>>>>>>>>>>>>>>runner 1");
            Stagiaire stagiaire = new Stagiaire();
            stagiaire.setNom("toto9");
            stagiaire.setPrenom("totop9");
            stagiaire.setGrade(Grade.ADJ);
            stagiaire.setAppartenance(Appartenance.TERRE);
            stagiaire.setNid("5643217891");
            log.trace(">>>>>>>>>>>>>>>>>>>runner 2");
//            titi();
            log.trace(">>>>>>>>>>>>>>>>>>>runner 3");
            stagiaireRepo.save(stagiaire);
            log.trace(">>>>>>>>>>>>>>>>>>>runner 4");
            Stage stage = new Stage();

            stage.setCodeStage("CG91");
            stage.setDateDebut(LocalDate.now().minusDays(1));
            stage.setDateFin(LocalDate.now().plusDays(30));
            stage.addStagiaire(stagiaire);
            Formateur formateur = new Formateur();
            formateur.setNom("TOTO");
            formateur.setPrenom("toto p");
            formateur.setGrade(Grade.ADJ);
            formateurRepo.save(formateur);
            stage.setCdsf(formateur);
//            stageRepo.save(stage);
            log.trace("recherche stage CG91" + iStageFacade.findByCodeStage("CG91"));

            iStageFacade.save(stage);

            Stage stage2 = new Stage();
            stage2.setCodeStage("GJ50");
            stage2.setDateDebut(LocalDate.now().minusDays(1));
            stage2.setDateFin(LocalDate.now().plusDays(30));
            stage2.addStagiaire(stagiaire);
            Formateur formateur2 = new Formateur();
            formateur2.setNom("TOTO2");
            formateur2.setPrenom("toto2 p");
            formateur2.setGrade(Grade.MAJ);
            formateurRepo.save(formateur2);
            stage2.setCdsf(formateur2);
//            stageRepo.save(stage);
            log.trace("recherche stage GJ50" + iStageFacade.findByCodeStage("GJ50"));

            iStageFacade.save(stage2);


//        stageRepo.saveAndFlush(stage);
        } catch (Exception e) {
            e.printStackTrace();
            log.trace(e.getMessage());
        }
    }

    private void titi() {
        log.trace("### getUsernameOfAuthenticatedUser");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        log.trace("authentication.getPrincipal()).getUsername() " + ((MyUserDetails) authentication.getPrincipal()).getUsername());
    }
}
