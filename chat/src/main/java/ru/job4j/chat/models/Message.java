package ru.job4j.chat.models;

import lombok.*;
import ru.job4j.chat.validators.TargetValidated;
import ru.job4j.chat.validators.ValueValid;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "messages")
@Getter
@Setter
@NoArgsConstructor
@ToString(of = {"id", "text", "person", "room"})
@EqualsAndHashCode(of = "id")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Positive(groups = {TargetValidated.MessageUpdate.class}, message = "Id can positive")
    @ValueValid(groups = {TargetValidated.MessageCreate.class}, message = "For create Id can = 0")
    private long id;
    @NotBlank
    private String text;
    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    @NotNull
    @Valid
    private Person person;
    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    @NotNull
    @Valid
    private Room room;
}
