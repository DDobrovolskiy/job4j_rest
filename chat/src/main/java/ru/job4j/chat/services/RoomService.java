package ru.job4j.chat.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.chat.dto.RoomDTO;
import ru.job4j.chat.models.Message;
import ru.job4j.chat.models.Person;
import ru.job4j.chat.models.Role;
import ru.job4j.chat.models.Room;
import ru.job4j.chat.repositories.MessageRepository;
import ru.job4j.chat.repositories.PersonRepository;
import ru.job4j.chat.repositories.RoomRepository;

import java.util.Optional;
import java.util.Set;

@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final PersonRepository personRepository;
    private final MessageRepository messageRepository;

    public RoomService(RoomRepository roomRepository,
                       PersonRepository personRepository,
                       MessageRepository messageRepository) {
        this.roomRepository = roomRepository;
        this.personRepository = personRepository;
        this.messageRepository = messageRepository;
    }

    public Set<Room> findAll() {
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
        var person = personRepository.findById(idPerson);
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
        var person = personRepository.findById(idPerson);
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

    public Set<Room> findAll(Set<Long> ids) {
        return roomRepository.findAllById(ids);
    }

    @Transactional
    public void patchRoom(RoomDTO roomDTO) {
        var room = roomRepository
                .findById(roomDTO.getId())
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                "Room not found, id: " + roomDTO.getId()));
        if (roomDTO.getName() != null) {
            room.setName(roomDTO.getName());
        }
        if (roomDTO.getMessagesID() != null) {
            Set<Message> messages = messageRepository.findAllById(roomDTO.getMessagesID());
            room.setMessages(messages);
        }
        if (roomDTO.getPersonsID() != null) {
            Set<Person> persons = personRepository.findAllById(roomDTO.getPersonsID());
            room.setPersons(persons);
        }
    }
}
