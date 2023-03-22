package net.ent.etrs.gestionstagiaire;


import lombok.extern.apachecommons.CommonsLog;
import net.ent.etrs.gestionstagiaire.model.entities.*;
import net.ent.etrs.gestionstagiaire.model.entities.references.Appartenance;
import net.ent.etrs.gestionstagiaire.model.entities.references.Grade;
import net.ent.etrs.gestionstagiaire.model.entities.references.TypeIF;
import net.ent.etrs.gestionstagiaire.model.facades.FacadeIngenierieFormation;
import net.ent.etrs.gestionstagiaire.model.facades.FacadeStage;
import net.ent.etrs.gestionstagiaire.model.facades.api.dto.DtoUtils;
import net.ent.etrs.gestionstagiaire.model.facades.api.dto.StagiaireDto;
import net.ent.etrs.gestionstagiaire.model.repo.FormateurRepo;
import net.ent.etrs.gestionstagiaire.model.repo.StagiaireRepo;
import net.ent.etrs.gestionstagiaire.model.repo.UserRepo;
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
    public FacadeStage iFacadeStage;
    @Autowired
    public FormateurRepo formateurRepo;
    @Autowired
    public FacadeIngenierieFormation facadeIngenierieFormation;
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
            if (userRepo.count() == 0) {
                userRepo.save(MyUserDetails.builder().username("ADMIN").password(encoder.encode("ADMIN")).accountNonExpired(true).credentialsNonExpired(true).accountNonLocked(true).enabled(true).build());
                userRepo.save(MyUserDetails.builder().username("ADMIN2").password(encoder.encode("ADMIN2")).enabled(true).build());
                userRepo.save(MyUserDetails.builder().username("ADMIN3").password(encoder.encode("ADMIN3")).accountNonExpired(true).credentialsNonExpired(true).accountNonLocked(true).enabled(true).build());
                userRepo.save(MyUserDetails.builder().username("ADMIN4").password(encoder.encode("ADMIN4")).accountNonExpired(false).accountNonLocked(true).credentialsNonExpired(false).enabled(true).build());
            }

            if (facadeIngenierieFormation.count() == 0) {
                IngenierieFormation ingenierieFormation = new IngenierieFormation();
                ingenierieFormation.setLibelle("PDI 3.a");
                ingenierieFormation.setDateDebut(LocalDate.of(2020, 01, 01));
                ingenierieFormation.setDateFin(LocalDate.of(2025, 12, 31));
                ingenierieFormation.setTypeIF(TypeIF.FS1);
                facadeIngenierieFormation.save(ingenierieFormation);
            }

            if (stagiaireRepo.count() == 0) {
                StagiaireDto stagiaireDto = new StagiaireDto();
                stagiaireDto.setNid("0123456789");
                stagiaireDto.setNom("0123456789");
                stagiaireDto.setPrenom("0123456789");
                stagiaireDto.setDateNaissance(LocalDate.now().minusYears(18));
                stagiaireDto.setMatricule("0337010562");
                stagiaireDto.setGrade(Grade.SCH);
                stagiaireDto.setAppartenance(Appartenance.AIR);
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
            }
            log.trace(">>>>>>>>>>>>>>>>>>>runner 4");
            if (iFacadeStage.count() == 0) {
                Stage stage = new Stage();

                stage.setCodeStage("CG91");
                stage.setDateDebut(LocalDate.now().minusDays(1));
                stage.setDateFin(LocalDate.now().plusDays(30));
                stage.addStagiaire(stagiaireRepo.findStagiaireByNid("5643217891").get());
                Formateur formateur = new Formateur();
                formateur.setNom("TOTO");
                formateur.setPrenom("toto p");
                formateur.setGrade(Grade.ADJ);
                formateurRepo.save(formateur);
                stage.setCdsf(formateur);
                stage.setIngenierieFormation(facadeIngenierieFormation.findById(1L).get());
//            stageRepo.save(stage);
                log.trace("recherche stage CG91" + iFacadeStage.findByCodeStage("CG91"));

                iFacadeStage.save(stage);

                Stage stage2 = new Stage();
                stage2.setCodeStage("GJ50");
                stage2.setDateDebut(LocalDate.now().minusDays(1));
                stage2.setDateFin(LocalDate.now().plusDays(30));
                stage2.addStagiaire(stagiaireRepo.findStagiaireByNid("5643217891").get());
                Formateur formateur2 = new Formateur();
                formateur2.setNom("TOTO2");
                formateur2.setPrenom("toto2 p");
                formateur2.setGrade(Grade.MAJ);
                formateurRepo.save(formateur2);
                stage2.setCdsf(formateur2);
                stage2.setIngenierieFormation(facadeIngenierieFormation.findById(1L).get());
//            stageRepo.save(stage);
                log.trace("recherche stage GJ50" + iFacadeStage.findByCodeStage("GJ50"));

                iFacadeStage.save(stage2);

            }
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
