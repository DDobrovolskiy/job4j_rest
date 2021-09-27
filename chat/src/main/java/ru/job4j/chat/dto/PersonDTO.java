package ru.job4j.chat.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class PersonDTO {
    private long id;
    private String name;
    private String password;
    private List<Long> rolesID = new LinkedList<>();
    private List<Long> messagesID = new LinkedList<>();
    private List<Long> roomsID = new LinkedList<>();
}
