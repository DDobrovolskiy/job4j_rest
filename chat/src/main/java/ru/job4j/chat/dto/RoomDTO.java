package ru.job4j.chat.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class RoomDTO {
    private long id;
    private String name;
    private Set<Long> messagesID = new HashSet<>();
    private Set<Long> personsID = new HashSet<>();
}
