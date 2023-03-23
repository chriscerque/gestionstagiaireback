package net.ent.etrs.gestionstagiaire;


import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import net.ent.etrs.gestionstagiaire.controller.dto.StagiaireDto;
import net.ent.etrs.gestionstagiaire.model.entities.*;
import net.ent.etrs.gestionstagiaire.model.entities.references.Appartenance;
import net.ent.etrs.gestionstagiaire.model.entities.references.Grade;
import net.ent.etrs.gestionstagiaire.model.entities.references.TypeIF;
import net.ent.etrs.gestionstagiaire.model.repo.FormateurRepo;
import net.ent.etrs.gestionstagiaire.model.repo.UserRepo;
import net.ent.etrs.gestionstagiaire.model.services.ServiceIngenierieFormation;
import net.ent.etrs.gestionstagiaire.model.services.ServiceStage;
import net.ent.etrs.gestionstagiaire.model.services.ServiceStagiaire;
import net.ent.etrs.gestionstagiaire.model.services.exceptions.BusinessException;
import net.ent.etrs.gestionstagiaire.security.services.MyUserDetailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
@CommonsLog(topic = "SOUT")
public class MyRunner2 implements CommandLineRunner {

    //    @Autowired
//    public StageRepo stageRepo;
    @NonNull
    public ServiceStage iServiceStage;
    @NonNull
    public FormateurRepo formateurRepo;
    @NonNull
    public ServiceIngenierieFormation serviceIngenierieFormation;
    @NonNull
    private UserRepo userRepo;
    //    @Autowired
//    private StagiaireRepo stagiaireRepo;
    @NonNull
    private ServiceStagiaire serviceStagiaire;

    @NonNull
    private ServiceStage serviceStage;
    @NonNull
    private MyUserDetailService userDetailsService;

    @NonNull
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

            if (serviceIngenierieFormation.count() == 0) {
                IngenierieFormation ingenierieFormation = new IngenierieFormation();
                ingenierieFormation.setLibelle("PDI 3.a");
                ingenierieFormation.setDateDebut(LocalDate.of(2020, 01, 01));
                ingenierieFormation.setDateFin(LocalDate.of(2025, 12, 31));
                ingenierieFormation.setTypeIF(TypeIF.FS1);
                serviceIngenierieFormation.save(ingenierieFormation);
            }

            if (serviceStagiaire.count() == 0) {
                StagiaireDto stagiaireDto = new StagiaireDto();
                stagiaireDto.setNid("0123456789");
                stagiaireDto.setNom("0123456789");
                stagiaireDto.setPrenom("0123456789");
                stagiaireDto.setDateNaissance(LocalDate.now().minusYears(18));
                stagiaireDto.setMatricule("0337010562");
                stagiaireDto.setGrade(Grade.SCH);
                stagiaireDto.setAppartenance(Appartenance.AIR);
                serviceStagiaire.save(stagiaireDto);


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
                serviceStagiaire.save(serviceStagiaire.toDto(stagiaire));
            }
            log.trace(">>>>>>>>>>>>>>>>>>>runner 4");
            if (iServiceStage.count() == 0) {
                Stage stage = new Stage();

                stage.setCodeStage("CG91");
                stage.setDateDebut(LocalDate.now().minusDays(1));
                stage.setDateFin(LocalDate.now().plusDays(30));
                stage.addStagiaire(serviceStagiaire.fromDto(serviceStagiaire.findStagiaireByNid("5643217891").get()));
                Formateur formateur = new Formateur();
                formateur.setNom("TOTO");
                formateur.setPrenom("toto p");
                formateur.setGrade(Grade.ADJ);
                formateurRepo.save(formateur);
                stage.setCdsf(formateur);
                stage.setIngenierieFormation(serviceIngenierieFormation.findById(1L).get());
//            stageRepo.save(stage);

                iServiceStage.save(serviceStage.toDto(stage));

                log.trace("recherche stage CG91" + iServiceStage.findByCodeStage("CG91"));

                Stage stage2 = new Stage();
                stage2.setCodeStage("GJ50");
                stage2.setDateDebut(LocalDate.now().minusDays(1));
                stage2.setDateFin(LocalDate.now().plusDays(30));
                stage2.addStagiaire(serviceStagiaire.fromDto(serviceStagiaire.findStagiaireByNid("5643217891").get()));
                Formateur formateur2 = new Formateur();
                formateur2.setNom("TOTO2");
                formateur2.setPrenom("toto2 p");
                formateur2.setGrade(Grade.MAJ);
                formateurRepo.save(formateur2);
                stage2.setCdsf(formateur2);
                stage2.setIngenierieFormation(serviceIngenierieFormation.findById(1L).get());
//            stageRepo.save(stage);

                iServiceStage.save(serviceStage.toDto(stage2));
                log.trace("recherche stage GJ50" + iServiceStage.findByCodeStage("GJ50"));

            }
//        stageRepo.saveAndFlush(stage);
        } catch (Exception | BusinessException e) {
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
