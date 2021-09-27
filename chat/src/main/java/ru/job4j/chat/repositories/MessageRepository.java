package ru.job4j.chat.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.chat.models.Message;
import ru.job4j.chat.models.Room;

import java.util.List;
import java.util.Set;

public interface MessageRepository extends CrudRepository<Message, Long> {
    Set<Message> findAll();

    Set<Message> findAllById(Iterable<Long> ids);

    List<Message> findByPersonId(Long personId);

    List<Message> findByRoomId(Long roomId);
}
