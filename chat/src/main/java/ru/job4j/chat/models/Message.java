package ru.job4j.chat.models;

import lombok.*;

import javax.persistence.*;

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
    private long id;
    private String text;
    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;
    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;
}
