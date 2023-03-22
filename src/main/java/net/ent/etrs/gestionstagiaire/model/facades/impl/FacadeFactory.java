package net.ent.etrs.gestionstagiaire.model.facades.impl;

import net.ent.etrs.gestionstagiaire.model.facades.FacadeGrade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

//@Component
//@Scope("singleton")
//@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Configuration
public class FacadeFactory {

    public FacadeFactory() {
//        System.out.println("$$$$$$$ FacadeFactory/constructor");
//        System.out.println("$$$$$$$>>>>> FacadeFactory/this : " + this);
    }

    @Bean(name = "GradeFacade")
    @Scope("singleton")
    public static FacadeGrade getGradeFacade() {
//        System.out.println("$$$$$$$ FacadeFactory/getGradeFacade");
        FacadeGrade facadeGrade = new FacadeGradeImpl();
//        System.out.println("$$$$$$$>>>>> gradeFacade : " + gradeFacade);
        return facadeGrade;
    }

    //    @Bean(name = "FacadeFormateur")
//    @Bean
//    @Scope("singleton")
//    public static FacadeFormateur getFacadeFormateur() {
////        System.out.println("$$$$$$$ FacadeFactory/getGradeFacade");
//        FacadeFormateur facadeFormateur = new FacadeFormateurImpl();
////        System.out.println("$$$$$$$>>>>> gradeFacade : " + gradeFacade);
//        return facadeFormateur;
//    }
//
//    @Bean
//    @Scope("singleton")
//    public static FacaceNote getFacadeNote() {
////        System.out.println("$$$$$$$ FacadeFactory/getGradeFacade");
//        FacaceNote facaceNote = new FacadeNoteImpl();
////        System.out.println("$$$$$$$>>>>> gradeFacade : " + gradeFacade);
//        return facaceNote;
//    }
//
//    @Bean
//    @Scope("singleton")
//    public static FacadeIngenierieFormation getFacaceIngenierieFormation() {
////        System.out.println("$$$$$$$ FacadeFactory/getGradeFacade");
//        FacadeIngenierieFormation facadeIngenierieFormation = new FacadeIngenierieFormationImpl();
////        System.out.println("$$$$$$$>>>>> gradeFacade : " + gradeFacade);
//        return facadeIngenierieFormation;
//    }

//    @Bean(name = "IStagiaireFacade")
//    @Scope("singleton")
//    public static IStagiaireFacade getStagaireFacade(){
////        System.out.println("$$$$$$$ FacadeFactory/getStagaireFacade");
//        StagiaireFacade stagiaireFacade = new StagiaireFacade();
////        System.out.println("$$$$$$$>>>>> stagiaireFacade : " + stagiaireFacade);
//        return stagiaireFacade;
//    }

//    @Bean(name = "IStageFacade")
//    @Scope("singleton")
//    public static IStageFacade getStageFacade() {
////        System.out.println("$$$$$$$ FacadeFactory/getStageFacade");
//        StageFacade stageFacade = new StageFacade();
////        System.out.println("$$$$$$$>>>>> stageFacade : " + stageFacade);
//        return stageFacade;
//    }

//    public static IGradeFacade getGradeFacade(){
//        return new GradeFacade();
//    }


}
