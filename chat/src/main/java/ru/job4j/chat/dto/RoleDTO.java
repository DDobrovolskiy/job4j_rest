package ru.job4j.chat.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class RoleDTO {
    private long id;
    private String title;
    private Set<Long> personsID = new HashSet<>();
}
