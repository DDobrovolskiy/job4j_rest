package ru.job4j.chat.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "rooms")
@Getter
@Setter
@NoArgsConstructor
@ToString(of = {"id", "name"})
@EqualsAndHashCode(of = "id")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @JsonIgnore
    @OneToMany(mappedBy = "room", orphanRemoval = true)
    private Set<Message> messages = new HashSet<>();
    @JsonIgnore
    @ManyToMany
    private Set<Person> persons = new HashSet<>();
}
