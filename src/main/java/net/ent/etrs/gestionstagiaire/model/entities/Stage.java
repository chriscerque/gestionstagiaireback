package net.ent.etrs.gestionstagiaire.model.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.ent.etrs.gestionstagiaire.model.entities.references.Cmodel;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "STAGE")
@EqualsAndHashCode(callSuper = false, of = "codeStage")
@ToString(exclude = "stagiaireList")
public class Stage extends AbstractEntity{

    @Getter @Setter
    @ManyToOne
    @JoinColumn(name = "IF_ID", foreignKey = @ForeignKey(name = "FK_STAGE_IF_ID"))
//    @NotNull(message = C_MODEL.BV_STAGE_IF_NOT_NULL)
    private IngenierieFormation ingenierieFormation;

    @Getter @Setter
    @Column(name = "CODE_STAGE",unique = true, nullable = false)
    @NotBlank(message = Cmodel.BV_STAGE_CODE_STAGE_NOT_NULL)
    private String codeStage;

    @Getter @Setter
//    @Convert(converter = LocalDateConverter.class)
    @Column(name = "DATE_DEBUT", nullable = false)
    @NotNull(message = Cmodel.BV_STAGE_DATE_DEBUT_NOT_NULL)
    private LocalDate dateDebut;

    @Getter @Setter
//    @Convert(converter = LocalDateConverter.class)
    @Column(name = "DATE_FIN", nullable = false)
    @NotNull(message = Cmodel.BV_STAGE_DATE_FIN_NOT_NULL)
    private LocalDate dateFin;

    @Getter @Setter
    @ManyToOne
    @JoinColumn(name = "CDSF_ID", nullable = false, foreignKey = @ForeignKey(name = "FK_STAGE_CDSF_ID"))
    @NotNull(message = Cmodel.BV_STAGE_CDSF_NOT_NULL)
    private Formateur cdsf;


    @ManyToMany
    @JoinTable(
            name = "STAGE_STAGIAIRE",
            joinColumns = @JoinColumn(name = "STAGE_ID"),
            foreignKey = @ForeignKey(name = "FK_STAGE_STAGIAIRE_STAGE_ID"),
            inverseJoinColumns = @JoinColumn(name = "STAGIAIRE_ID"),
            inverseForeignKey = @ForeignKey(name = "FK_STAGE_STAGIAIRE_STAGIAIRE_ID")
    )
    private List<Stagiaire> stagiaireList = new ArrayList<>();

    public void addStagiaire(@Valid Stagiaire stagiaire){
        this.stagiaireList.add(stagiaire);
    }

    public void supprStagiaire(@Valid Stagiaire stagiaire){
        this.stagiaireList.remove(stagiaire);
    }

}
