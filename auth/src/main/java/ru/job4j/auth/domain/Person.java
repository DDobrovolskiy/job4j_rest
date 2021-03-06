package ru.job4j.auth.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "persons")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Person implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NonNull
    @Column(unique = true, nullable = false)
    private String login;
    @NonNull
    @Column(nullable = false)
    private String password;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
