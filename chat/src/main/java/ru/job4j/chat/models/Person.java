package ru.job4j.chat.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "persons")
@Getter
@Setter
@NoArgsConstructor
@ToString(of = {"id", "name", "password"})
@EqualsAndHashCode(of = "id")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String password;
    @JsonIgnore
    @ManyToMany
    private List<Role> roles = new LinkedList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "person")
    private List<Message> messages = new LinkedList<>();
    @JsonIgnore
    @ManyToMany(mappedBy = "persons")
    private List<Room> rooms = new LinkedList<>();
}
