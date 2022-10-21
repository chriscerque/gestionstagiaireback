package net.ent.etrs.gestionstagiaire.model.services;

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

    @Bean(name = "IGradeFacade")
    @Scope("singleton")
    public static IGradeFacade getGradeFacade(){
//        System.out.println("$$$$$$$ FacadeFactory/getGradeFacade");
        GradeFacade gradeFacade = new GradeFacade();
//        System.out.println("$$$$$$$>>>>> gradeFacade : " + gradeFacade);
        return gradeFacade;
    }

    @Bean(name = "IStagiaireFacade")
    @Scope("singleton")
    public static IStagiaireFacade getStagaireFacade(){
//        System.out.println("$$$$$$$ FacadeFactory/getStagaireFacade");
        StagiaireFacade stagiaireFacade = new StagiaireFacade();
//        System.out.println("$$$$$$$>>>>> stagiaireFacade : " + stagiaireFacade);
        return stagiaireFacade;
    }

    @Bean(name = "IStageFacade")
    @Scope("singleton")
    public static IStageFacade getStageFacade() {
//        System.out.println("$$$$$$$ FacadeFactory/getStageFacade");
        StageFacade stageFacade = new StageFacade();
//        System.out.println("$$$$$$$>>>>> stageFacade : " + stageFacade);
        return stageFacade;
    }

//    public static IGradeFacade getGradeFacade(){
//        return new GradeFacade();
//    }


}
