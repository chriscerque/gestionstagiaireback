package net.ent.etrs.gestionstagiaire;

import lombok.extern.apachecommons.CommonsLog;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

@CommonsLog(topic = "SOUT")
public class LanceurCreateTable {

    public static void main(String[] args) {
        EntityManager em = Persistence.createEntityManagerFactory("PU_CREATE").createEntityManager();
        log.trace(">>>>>>>>>>>>>>>>>>> em : " + em);
    }

}
