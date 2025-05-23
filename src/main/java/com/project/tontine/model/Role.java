package com.project.tontine.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NoArgsConstructor;


import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "global_schema")
public class Role
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

    
    @ManyToOne
    @JoinColumn(nullable = false)
    private Group group;

    @ManyToOne
    @JoinColumn(nullable = false)
    private RoleType roleType;

    @JsonIgnore
    @JsonManagedReference
    @OneToMany(mappedBy = "role")
    private List<Possession> possessions;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
        name = "Possession",
        schema = "tontine_schema", 
        joinColumns = @JoinColumn(name = "role_id"),
        inverseJoinColumns = @JoinColumn(name = "member_id")
    )
    private List<Member> members;
}
    // @JsonIgnore
    // @ManyToMany
    // @JoinTable(
    //     name = "Grant",
    //     schema = "tontine_schema",
    //     joinColumns = @JoinColumn,
    //     inverseJoinColumns = @JoinColumn
    // )
    // private List<Permission> permissions;
