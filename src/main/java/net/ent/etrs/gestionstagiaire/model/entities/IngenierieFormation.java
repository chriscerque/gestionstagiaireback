package net.ent.etrs.gestionstagiaire.model.entities;


import lombok.Getter;
import lombok.Setter;
import net.ent.etrs.gestionstagiaire.model.entities.references.TypeIF;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;

@Entity
@Table(name = "INGENIERIE_FORMATION")
public class IngenierieFormation extends AbstractEntity {

    @OneToMany
    @JoinColumn(name = "IF_ID", foreignKey = @ForeignKey(name = "FK__UV__IF_ID"))
    Set<UniteValeur> uvSet;
    @Getter
    @Setter
    @Column(name = "LIBELLE", nullable = false)
    private String libelle;
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE_IF", nullable = false)
    private TypeIF typeIF;
    @Getter
    @Setter
//    @Convert(converter = LocalDateConverter.class)
    @Column(name = "DATE_DEBUT", nullable = false)
    private LocalDate dateDebut;
    @Getter
    @Setter
//    @Convert(converter = LocalDateConverter.class)
    @Column(name = "DATE_FIN", nullable = false)
    private LocalDate dateFin;

    public Set<UniteValeur> getUvSet() {
        return Collections.unmodifiableSet(this.uvSet);
    }

    public void setUvSet(Set<UniteValeur> uvSet) {
        this.uvSet = uvSet;
    }
}
