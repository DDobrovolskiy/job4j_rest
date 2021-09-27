package ru.job4j.chat.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class RoleDTO {
    private long id;
    private String title;
    private List<Long> personsID = new LinkedList<>();
}
