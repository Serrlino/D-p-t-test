package com.project.tontine.model;

import com.project.tontine.enumeration.RoleTypeLabel;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.Setter;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "global_schema")
public class RoleType
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
	private Integer id;

    @Column(length = 32, nullable = false, unique = true)
    private RoleTypeLabel label;

    @Column(length = 255)
    private String description;


    @JsonIgnore
    @OneToMany(mappedBy = "roleType", cascade = CascadeType.ALL)
    private List<Role> roles;

}
