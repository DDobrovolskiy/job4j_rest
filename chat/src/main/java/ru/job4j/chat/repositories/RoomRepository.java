package ru.job4j.chat.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.chat.models.Room;

import java.util.List;
import java.util.Set;

public interface RoomRepository extends CrudRepository<Room, Long> {
    Set<Room> findAll();

    Set<Room> findAllById(Iterable<Long> ids);
}
