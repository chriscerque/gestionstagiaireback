package net.ent.etrs.gestionstagiaire.model.entities.references;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Cmodel {

    public static final String BV_MATIERE_LIBELLE_NOT_NULL_NOT_EMPTY = "Le libellé de la matière doit être renseigné.";
    public static final String BV_EVALUATION_COEFF = "Le coefficient doit être positif.";
    public static final String BV_EVALUATION_TYPE_STAGIAIRE = "Le type stagiaire n'est pas valide.";
    public static final String BV_EVALUATION_MATIERE = "La matière doit être renseignée.";
    public static final String BV_STAGE_IF_NOT_NULL = "L'IF doit être renseignée.";
    public static final String BV_STAGE_CODE_STAGE_NOT_NULL = "Le code du stage doit être renseigné.";
    public static final String BV_STAGE_DATE_DEBUT_NOT_NULL = "La date de début du stage doit être renseignée.";
    public static final String BV_STAGE_DATE_FIN_NOT_NULL = "La date de fin du stage doit être renseignée.";
    public static final String BV_STAGE_CDSF_NOT_NULL = "Le CDSF doit être renseigné.";
    public static final String BV_STAGIAIRE_NOM_NOT_NULL = "Le nom doit être renseigné.";
    public static final String BV_STAGIAIRE_PRENOM_NOT_NULL = "Le prénom doit être renseigné.";
    public static final String BV_STAGIAIRE_GRADE_NOT_NULL = "Le grade doit être renseigné.";
    public static final String BV_NOTE_EVALUATION_NOT_NULL = "La note doit être renseignée.";
    public static final String BV_NOTE_VALEUR_POSITIVE_OR_ZERO = "La note doit être positive.";
    public static final String BV_NOTE_FORMATEUR_NOT_NULL = "Le formateur doit être renseigné.";
    public static final String BV_NOTE_DATE_NOT_NULL = "La date doit être renseignée.";
    public static final String BV_UNITE_VALEUR_INDICE_NOT_NULL = "L'UV doit être renseigné.";
    public static final String BV_UNITE_VALEUR_INDICE_POSITIVE_OR_ZERO = "La valeur de l'UV doit être positive.";
    public static final String BV_STAGIAIRE_APPARTENANCE_NOT_NULL = "L'appartenance doit être renseignée.";
    public static final String BV_STAGIAIRE_DATE_NAISSANCE_NOT_NULL = "La date de naissance doit être renseignée.";
    public static final String BV_STAGIAIRE_DATE_NAISSANCE_PAST = "La date de naissance ne peut être dans le passé.";
    public static final String STAGIAIRE_PATTERN_NID = "\\d{10}";
    public static final String STAGIAIRE_PATTERN_MATRICULE = "\\d{10}";
}
