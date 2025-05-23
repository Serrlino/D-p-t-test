package com.project.tontine.model;

import com.project.tontine.enumeration.GroupType;
import com.project.tontine.enumeration.Periodicity;
import lombok.Setter;
import lombok.AllArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.CascadeType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.persistence.Table;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name  = "groups", schema = "global_schema")
public class Group
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

    @Column(length = 32, nullable = false, unique = true)
    private String name;

    @Column(length = 255)
    private String description;

    @Column
    private Periodicity meetPeriodicity;

    @Column(length = 32, nullable = false)
    private GroupType type;

    @Column
    private Float ceiling;


    @ManyToOne
    @JoinColumn
    private Member member;

	@JsonIgnore
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<Role> roles;

    @JsonIgnore
    @OneToMany(mappedBy = "group")
    private List<Meeting> meetings;

    @JsonIgnore
    @OneToMany(mappedBy = "group")
    private List<PlannedMeeting> plannedMeetings;
}
