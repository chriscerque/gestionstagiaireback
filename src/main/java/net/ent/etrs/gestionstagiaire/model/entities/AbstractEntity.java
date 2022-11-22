package net.ent.etrs.gestionstagiaire.model.entities;


import lombok.*;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@SuppressWarnings("serial")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "id")
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@ToString
@CommonsLog(topic = "SOUT")
public class AbstractEntity /*extends AbstractAuditable<User, Long>*/ implements Serializable {
    // annotation lombok
    @Getter
    @Setter
    // annotations JPA
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    protected Long id;

//    @Column(name = "UUID")
//    protected String uuid = UUID.randomUUID().toString();

    @Column(name = "CREATED_BY_USER", nullable = false, updatable = false, columnDefinition = "VARCHAR(255) default 'ANONYMOUS'")
    @CreatedBy
    private String createdByUser;

    @Column(name = "MODIFIED_BY_USER", nullable = false, columnDefinition = "VARCHAR(255) default 'ANONYMOUS'")
    @LastModifiedBy
    private String modifiedByUser;

    @Column(name = "CREATED_DATE", nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column(name = "MODIFIED_DATE", nullable = false)
    @LastModifiedDate
    private LocalDateTime modifiedDate = LocalDateTime.now();

//    @PrePersist
//    public void prePersist() {
//        String createdByUser = getUsernameOfAuthenticatedUser();
//        this.createdByUser = createdByUser;
//        this.modifiedByUser = createdByUser;
////        this.createdDate = LocalDate.now();
////        this.modifiedDate = LocalDate.now();
//    }
//
//    @PreUpdate
//    public void preUpdate() {
//        String modifiedByUser = getUsernameOfAuthenticatedUser();
//        this.modifiedByUser = modifiedByUser;
////        this.modifiedDate = LocalDate.now();
//    }

    private String getUsernameOfAuthenticatedUser() {
        log.trace("### getUsernameOfAuthenticatedUser");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.trace("authentication : " + authentication);
        log.trace("authentication.getPrincipal()).getUsername() " + ((MyUserDetails) authentication.getPrincipal()).getUsername());
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }

        return ((MyUserDetails) authentication.getPrincipal()).getUsername();
    }

}
