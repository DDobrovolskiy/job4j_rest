package ru.job4j.chat.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

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
    private long id;
    @Column(unique = true, nullable = false)
    private String title;
    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private List<Person> persons = new LinkedList<>();

    @Override
    public String getAuthority() {
        return getTitle();
    }
}
