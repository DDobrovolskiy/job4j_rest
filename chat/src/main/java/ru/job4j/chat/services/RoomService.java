package ru.job4j.chat.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.chat.models.Room;
import ru.job4j.chat.repositories.RoomRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private PersonService personService;

    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    public Optional<Room> findById(long id) {
        return roomRepository.findById(id);
    }

    public Room save(Room room) {
        return roomRepository.save(room);
    }

    public void update(Room room) {
        save(room);
    }

    public void delete(long id) {
        roomRepository.deleteById(id);
    }

    @Transactional
    public boolean personInsertRoom(long idPerson, long idRoom) {
        var person = personService.findById(idPerson);
        var room = this.findById(idRoom);
        if (person.isPresent() && room.isPresent()) {
            var list = room.get().getPersons();
            if (!list.contains(person.get())) {
                list.add(person.get());
                return true;
            }
        }
        return false;
    }

    @Transactional
    public boolean personDeleteRoom(long idPerson, long idRoom) {
        var person = personService.findById(idPerson);
        var room = this.findById(idRoom);
        if (person.isPresent() && room.isPresent()) {
            var list = room.get().getPersons();
            if (list.contains(person.get())) {
                list.remove(person.get());
                return true;
            }
        }
        return false;
    }
}
