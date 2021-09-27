package ru.job4j.chat.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class RoomDTO {
    private long id;
    private String name;
    private List<Long> messagesID = new LinkedList<>();
    private List<Long> personsID = new LinkedList<>();
}
