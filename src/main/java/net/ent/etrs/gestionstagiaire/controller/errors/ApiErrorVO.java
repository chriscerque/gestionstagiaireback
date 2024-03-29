package net.ent.etrs.gestionstagiaire.controller.errors;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@JsonInclude(content = JsonInclude.Include.NON_NULL)
//@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ApiErrorVO {
    private String errorCode;
    private String message;


}
