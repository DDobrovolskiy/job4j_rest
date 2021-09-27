package ru.job4j.chat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.chat.dto.MessageDTO;
import ru.job4j.chat.dto.RoleDTO;
import ru.job4j.chat.models.Message;
import ru.job4j.chat.services.MessageService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/messages")
public class MessageControl {
    @Autowired
    private MessageService messageService;

    @GetMapping()
    public Set<Message> getAllMessage() {
        return messageService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> getIdMessage(@PathVariable long id) {
        var message = messageService.findById(id);
        return new ResponseEntity<>(
                message.orElse(new Message()),
                message.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }

    @PostMapping()
    public ResponseEntity<Message> postMessage(@RequestBody Message message) {
        return new ResponseEntity<>(messageService.save(message), HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<Void> updateMessage(@RequestBody Message message) {
        messageService.update(message);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable long id) {
        messageService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/room/{id}")
    public List<Message> getMessagesRoomId(@PathVariable long id) {
        return messageService.findByRoomId(id);
    }

    @GetMapping("/person/{id}")
    public List<Message> getMessagesPersonId(@PathVariable long id) {
        return messageService.findByPersonId(id);
    }

    @PatchMapping()
    public ResponseEntity<Void> patch(@RequestBody MessageDTO messageDTO) {
        messageService.patchMessage(messageDTO);
        return ResponseEntity.ok().build();
    }
}
