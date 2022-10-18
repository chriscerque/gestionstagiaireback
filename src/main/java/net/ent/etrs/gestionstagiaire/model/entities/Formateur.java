package net.ent.etrs.gestionstagiaire.model.entities;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.ent.etrs.gestionstagiaire.model.entities.references.Grade;

import javax.persistence.*;

@Entity
@Table(name = "FORMATEUR")
@ToString
public class Formateur extends AbstractEntity{

    @Getter
    @Setter
    @Column(name = "NOM_F", nullable = false)
    private String nom;

    @Getter @Setter
    @Column(name = "PRENOM_F", nullable = false)
    private String prenom;

    @Getter @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "GRADE_F", nullable = false)
    private Grade grade;

}
