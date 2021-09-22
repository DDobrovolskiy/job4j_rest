package ru.job4j.chat.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

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
    private List<Message> messages = new LinkedList<>();
    @JsonIgnore
    @ManyToMany
    private List<Person> persons = new LinkedList<>();
}
