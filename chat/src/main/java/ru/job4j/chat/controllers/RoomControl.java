package ru.job4j.chat.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.job4j.chat.dto.PersonDTO;
import ru.job4j.chat.dto.RoomDTO;
import ru.job4j.chat.models.Person;
import ru.job4j.chat.models.Room;
import ru.job4j.chat.services.RoomService;
import ru.job4j.chat.validators.TargetValidated;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/rooms")
@Slf4j
@Validated
public class RoomControl {

    @Autowired
    private RoomService roomService;
    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping()
    public Set<Room> findAll() {
        return roomService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> findById(@PathVariable @Positive long id) {
        var room = roomService.findById(id);
        return ResponseEntity.of(room);
    }

    @PostMapping()
    @Validated({TargetValidated.RoomCreate.class})
    public ResponseEntity<Room> save(@RequestBody @Valid Room room) {
        return new ResponseEntity<>(
                roomService.save(room),
                HttpStatus.CREATED);
    }

    @PutMapping()
    @Validated({TargetValidated.RoomUpdate.class})
    public ResponseEntity<Void> update(@RequestBody @Valid Room room) {
        roomService.update(room);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @Positive long id) {
        roomService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{idRoom}/person")
    @Validated({TargetValidated.RoomUpdate.class})
    public ResponseEntity<Void> insertPersonInRoom(
            @PathVariable @Positive long idRoom,
            @RequestBody @Valid Person person) {
        if (roomService.personInsertRoom(person.getId(), idRoom)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw new IllegalArgumentException("Person or Room not find");
        }
    }

    @DeleteMapping("/{idRoom}/person/{idPerson}")
    public ResponseEntity<Void> deletePersonInRoom(
            @PathVariable @Positive long idRoom,
            @PathVariable @Positive long idPerson) {
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

    @PatchMapping()
    public ResponseEntity<Void> patch(@RequestBody RoomDTO roomDTO) {
        roomService.patchRoom(roomDTO);
        return ResponseEntity.ok().build();
    }
}
