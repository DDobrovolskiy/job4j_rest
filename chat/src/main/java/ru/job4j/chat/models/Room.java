package ru.job4j.chat.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
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
@Table(name = "rooms")
@Getter
@Setter
@NoArgsConstructor
@ToString(of = {"id", "name"})
@EqualsAndHashCode(of = "id")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Positive(groups = {TargetValidated.RoomUpdate.class}, message = "Id can positive")
    @ValueValid(groups = {TargetValidated.RoomCreate.class}, message = "For create Id can = 0")
    private long id;
    @NotBlank
    private String name;
    @JsonIgnore
    @OneToMany(mappedBy = "room", orphanRemoval = true)
    private Set<Message> messages = new HashSet<>();
    @JsonIgnore
    @ManyToMany
    private Set<Person> persons = new HashSet<>();
}
