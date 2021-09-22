package ru.job4j.chat.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.chat.models.Message;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Long> {
    List<Message> findAll();

    List<Message> findByPersonId(Long personId);

    List<Message> findByRoomId(Long roomId);
}
