package net.ent.etrs.gestionstagiaire.model.facades.api.dto;


import lombok.ToString;
import net.ent.etrs.gestionstagiaire.model.entities.references.Appartenance;
import net.ent.etrs.gestionstagiaire.model.entities.references.Grade;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@ToString
public class StagiaireDto {

    public String nom;

    public String prenom;

    public Grade grade;

    public Appartenance appartenance;

    public LocalDate dateNaissance;

    public String matricule;

    public String nid;

    public Integer numBatiment;

    public Integer numChambre;

    public Integer numBadgeMess;

    public Integer numBadgeAcces;

    public Set<NoteDto> noteList = new HashSet<>();

    public void ajouterNote(NoteDto note){
        this.noteList.add(note);
    }

    public void supprimerNote(NoteDto note){
        this.noteList.remove(note);
    }
}
