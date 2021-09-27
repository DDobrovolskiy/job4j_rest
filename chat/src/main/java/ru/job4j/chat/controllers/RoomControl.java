package ru.job4j.chat.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.chat.models.Person;
import ru.job4j.chat.models.Room;
import ru.job4j.chat.services.RoomService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/rooms")
@Slf4j
public class RoomControl {

    @Autowired
    private RoomService roomService;
    @Autowired
    private ObjectMapper objectMapper;

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
        if (roomService.personInsertRoom(person.getId(), idRoom)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw new IllegalArgumentException("Person or Room not find");
        }
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

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public void exceptionSpecificity(Exception e, HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setStatus(HttpStatus.NOT_FOUND.value());
        resp.setContentType("application/json");
        resp.getWriter().write(objectMapper.writeValueAsString(new HashMap<>() { {
            put("message", e.getMessage());
            put("type", e.getClass());
        }}));
        log.error(e.getLocalizedMessage());
    }
}
