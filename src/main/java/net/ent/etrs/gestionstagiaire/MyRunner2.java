package net.ent.etrs.gestionstagiaire;


import net.ent.etrs.gestionstagiaire.model.entities.Formateur;
import net.ent.etrs.gestionstagiaire.model.entities.MyUser;
import net.ent.etrs.gestionstagiaire.model.entities.Stage;
import net.ent.etrs.gestionstagiaire.model.entities.Stagiaire;
import net.ent.etrs.gestionstagiaire.model.entities.references.Appartenance;
import net.ent.etrs.gestionstagiaire.model.entities.references.Grade;
import net.ent.etrs.gestionstagiaire.model.services.IStageFacade;
import net.ent.etrs.gestionstagiaire.controllers.MyUserDetailService;
import net.ent.etrs.gestionstagiaire.controllers.dto.DtoUtils;
import net.ent.etrs.gestionstagiaire.controllers.dto.StagiaireDto;
import net.ent.etrs.gestionstagiaire.controllers.dto.UserDTO;
import net.ent.etrs.gestionstagiaire.model.repo.FormateurRepo;
import net.ent.etrs.gestionstagiaire.model.repo.StagiaireRepo;
import net.ent.etrs.gestionstagiaire.model.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
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


    @Value("${toto.var}")
    private String text;

//    @Autowired
//    public UserRepo userRepo;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(this.text);


        try {
            System.out.println(">>>>>>>>>>>>>>>>>>>runner 0");
            userDetailsService.save(new UserDTO("ADMIN", "ADMIN", true, true, true, true));
            userDetailsService.save(UserDTO.builder().username("ADMIN2").password("ADMIN2").enabled(true).build());
            userDetailsService.save(new UserDTO("ADMIN3", "ADMIN3", true, true, true, true));
            userDetailsService.save(new UserDTO("ADMIN4", "ADMIN4", false, true, false, true));

            StagiaireDto stagiaireDto = new StagiaireDto();
            stagiaireDto.nid = "0123456789";
            stagiaireDto.nom = "0123456789";
            stagiaireDto.prenom = "0123456789";
            stagiaireDto.dateNaissance = LocalDate.now().minusYears(18);
            stagiaireDto.matricule = "0337010562";
            stagiaireDto.grade = Grade.SCH;
            stagiaireDto.appartenance = Appartenance.AIR;
            stagiaireRepo.save(DtoUtils.stagiaireFromDto(stagiaireDto));


            System.out.println(">>>>>>>>>>>>>>>>>>>runner 1");
            Stagiaire stagiaire = new Stagiaire();
            stagiaire.setNom("toto9");
            stagiaire.setPrenom("totop9");
            stagiaire.setGrade(Grade.ADJ);
            stagiaire.setAppartenance(Appartenance.TERRE);
            System.out.println(">>>>>>>>>>>>>>>>>>>runner 2");
//            titi();
            System.out.println(">>>>>>>>>>>>>>>>>>>runner 3");
            stagiaireRepo.save(stagiaire);
            System.out.println(">>>>>>>>>>>>>>>>>>>runner 4");
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
            System.out.println("recherche stage CG91" + iStageFacade.readStage("CG91"));

            iStageFacade.saveStage(stage);

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
            System.out.println("recherche stage GJ50" + iStageFacade.readStage("GJ50"));

            iStageFacade.saveStage(stage2);


//        stageRepo.saveAndFlush(stage);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }

    private void titi() {
        System.out.println("### getUsernameOfAuthenticatedUser");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        System.out.println("authentication.getPrincipal()).getUsername() " + ((MyUser) authentication.getPrincipal()).getUsername());
    }
}
