package ru.job4j.chat.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageDTO {
    private long id;
    private String text;
    private Long personID;
    private Long roomID;
}
