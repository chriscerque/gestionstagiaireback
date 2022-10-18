package net.ent.etrs.gestionstagiaire.model.entities;


import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@SuppressWarnings("serial")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "id")
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractEntity /*extends AbstractAuditable<User, Long>*/ implements Serializable {
    // annotation lombok
    @Getter
    // annotations JPA
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    protected Long id;

//    @Column(name = "UUID")
//    protected String uuid = UUID.randomUUID().toString();

    @Column(name = "CREATED_BY_USER", nullable = false, columnDefinition = "VARCHAR(255) default 'ANONYMOUS'")
    @CreatedBy
    private String createdByUser;

    @Column(name = "MODIFIED_BY_USER", nullable = false, columnDefinition = "VARCHAR(255) default 'ANONYMOUS'")
    @LastModifiedBy
    private String modifiedByUser;

    @Column(name = "CREATED_DATE", nullable = false)
    @CreatedDate
    private LocalDate createdDate = LocalDate.now();

    @Column(name = "MODIFIED_DATE", nullable = false)
    @LastModifiedDate
    private LocalDate modifiedDate = LocalDate.now();

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
        System.out.println("### getUsernameOfAuthenticatedUser");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("authentication : " + authentication);
        System.out.println("authentication.getPrincipal()).getUsername() " + ((MyUser) authentication.getPrincipal()).getUsername());
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }

        return ((MyUser) authentication.getPrincipal()).getUsername();
    }

}
