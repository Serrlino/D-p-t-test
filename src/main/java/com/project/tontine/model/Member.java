package com.project.tontine.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Setter;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "global_schema")
public class Member implements UserDetails
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

    @Column(length = 32, nullable = false)
    private String name;

    @Column(length = 32)
    private String firstname;

    @Column(length = 15, unique = true)
    private Integer number;

    @Column(length = 32, nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Boolean enable = false;


	@JsonIgnore
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Account> accounts;

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Group> groups;

	@JsonIgnore
    @OneToMany(mappedBy = "creatorMember")
    private List<Meeting> createdMeetings;

    @JsonIgnore
    @JsonManagedReference
    @OneToMany(mappedBy = "recipientMember")
    private List<Meeting> doneMeetings;

    @JsonIgnore
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Activation> activations;

    @JsonIgnore
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<PlannedMeeting> plannedMeetings;

    @JsonIgnore
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Jwt> jwts;

    @JsonIgnore
    @OneToMany(mappedBy = "member", fetch = FetchType.EAGER)
    private List<Possession> possessions;

    @JsonIgnore
    @ManyToMany(mappedBy = "members", cascade = CascadeType.DETACH)
    private List<Role> roles;
    
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        Collection<? extends GrantedAuthority> authorities = getPossessions();

        if(authorities == null)
            return new ArrayList<>();

        return getPossessions();
    }

    @Override
    public String getPassword()
    {
        return password;
    }

    @Override
    public String getUsername()
    {
        return email;
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return enable;
    }
}
