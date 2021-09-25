package ru.job4j.chat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.chat.models.Person;
import ru.job4j.chat.models.Room;
import ru.job4j.chat.services.RoomService;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomControl {

    @Autowired
    private RoomService roomService;

    @GetMapping()
    public List<Room> findAll() {
        return roomService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> findById(@PathVariable long id) {
        var room = roomService.findById(id);
        return new ResponseEntity<>(
                room.orElse(new Room()),
                room.isPresent() ? HttpStatus.OK : HttpStatus.FOUND
        );
    }

    @PostMapping()
    public ResponseEntity<Room> save(@RequestBody Room room) {
        return new ResponseEntity<>(
                roomService.save(room),
                HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<Void> update(@RequestBody Room room) {
        roomService.update(room);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        roomService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{idRoom}/person")
    public ResponseEntity<Void> insertPersonInRoom(
            @PathVariable long idRoom,
            @RequestBody Person person) {
        return new ResponseEntity<>(
                roomService.personInsertRoom(person.getId(), idRoom)
                        ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }

    @DeleteMapping("/{idRoom}/person/{idPerson}")
    public ResponseEntity<Void> deletePersonInRoom(
            @PathVariable long idRoom,
            @PathVariable long idPerson) {
        return new ResponseEntity<>(
                roomService.personDeleteRoom(idPerson, idRoom)
                        ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }
}