package net.ent.etrs.gestionstagiaire;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class LanceurCreateTable {

    public static void main(String[] args) {
        EntityManager em = Persistence.createEntityManagerFactory("PU_CREATE").createEntityManager();
        System.out.println(">>>>>>>>>>>>>>>>>>> em : " + em);
    }

}
