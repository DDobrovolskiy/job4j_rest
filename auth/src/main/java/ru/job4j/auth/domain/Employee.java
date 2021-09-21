package ru.job4j.auth.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@ToString(of = {"id", "name", "surname", "inn", "employment"})
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    private Integer inn;
    private Date employment = Date.from(Instant.now());
    @OneToMany(mappedBy = "employee", orphanRemoval = true)
    @JsonIgnore
    private List<Person> persons = new LinkedList<>();
}
