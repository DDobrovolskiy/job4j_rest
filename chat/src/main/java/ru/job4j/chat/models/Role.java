package ru.job4j.chat.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import ru.job4j.chat.validators.TargetValidated;
import ru.job4j.chat.validators.ValueValid;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@ToString(of = {"id", "title"})
@EqualsAndHashCode(of = {"id"})
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Positive(groups = {TargetValidated.RoleUpdate.class}, message = "Id can positive")
    @ValueValid(groups = {TargetValidated.RoleCreate.class}, message = "For create Id can = 0")
    private long id;
    @Column(unique = true, nullable = false)
    @NotBlank
    private String title;
    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private Set<Person> persons = new HashSet<>();

    @Override
    public String getAuthority() {
        return getTitle();
    }
}
