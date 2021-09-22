package ru.job4j.chat.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.chat.models.Room;

import java.util.List;

public interface RoomRepository extends CrudRepository<Room, Long> {
    List<Room> findAll();
}
