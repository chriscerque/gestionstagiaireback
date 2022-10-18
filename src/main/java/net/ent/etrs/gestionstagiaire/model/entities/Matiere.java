package net.ent.etrs.gestionstagiaire.model.entities;


import lombok.Getter;
import lombok.Setter;
import net.ent.etrs.gestionstagiaire.model.entities.references.Cmodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "MATIERE")
public class Matiere extends AbstractEntity{

    @Getter
    @Setter
    @NotEmpty(message = Cmodel.BV_MATIERE_LIBELLE_NOT_NULL_NOT_EMPTY)
    @Column(name = "NOM", nullable = false,unique = true, columnDefinition = "VARCHAR(200)")
    private String nom;

}
