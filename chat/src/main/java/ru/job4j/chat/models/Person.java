package ru.job4j.chat.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "persons")
@Getter
@Setter
@NoArgsConstructor
@ToString(of = {"id", "name", "password"})
@EqualsAndHashCode(of = "id")
@Slf4j
public class Person implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true, nullable = false)
    private String name;
    @Column(nullable = false)
    private String password;
    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "person_role",
            joinColumns = {@JoinColumn(name = "person_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "role_id", nullable = false)})
    private Set<Role> roles = new HashSet<>();
    @JsonIgnore
    @OneToMany(mappedBy = "person")
    private Set<Message> messages = new HashSet<>();
    @JsonIgnore
    @ManyToMany(mappedBy = "persons")
    private Set<Room> rooms = new HashSet<>();

    public boolean addRole(Role role) {
        log.debug("Role add: {}", role);
        return roles.add(role);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getUsername() {
        return getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
