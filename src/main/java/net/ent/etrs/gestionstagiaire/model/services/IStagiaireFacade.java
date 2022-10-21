package net.ent.etrs.gestionstagiaire.model.services;

import net.ent.etrs.gestionstagiaire.model.entities.Stagiaire;

import java.util.List;

public interface IStagiaireFacade {

    Stagiaire saveStagiaire(Stagiaire stagiaire);
    void deleteStagiaire(Stagiaire stagiaire);
    Stagiaire readStagiaire(Long id);
    List<Stagiaire> readAllStagiaire();
    Stagiaire readStage(String nom, String prenom);


}
