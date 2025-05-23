package com.project.tontine.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Setter;
import lombok.AllArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.IdClass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@IdClass(IdPossession.class)
@Table(schema = "global_schema")
public class Possession implements GrantedAuthority
{
    @Id
    @ManyToOne()
    @JsonBackReference
    @JoinColumn
    private Member member;

    @Id
    @ManyToOne()
    @JsonBackReference
    @JoinColumn
    private Role role;

    @Override
    public String getAuthority() {
        return getRole().getRoleType().getLabel().name();
    }
}
