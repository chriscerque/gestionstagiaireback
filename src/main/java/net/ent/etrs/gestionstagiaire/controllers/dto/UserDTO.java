package net.ent.etrs.gestionstagiaire.controllers.dto;

import lombok.*;

/**
 * Possède les attributs de connexion.
 * username : login de l'utilisateur
 * password : mot de passe de l'utilisateur
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class UserDTO {

    /**
     * username : login de l'utilisateur
     */
    @NonNull
    private String username;
    /**
     * password : mot de passe de l'utilisateur
     */
    @NonNull
    private String password;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;


}
