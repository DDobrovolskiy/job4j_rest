package ru.job4j.chat.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.chat.dto.MessageDTO;
import ru.job4j.chat.models.Message;
import ru.job4j.chat.models.Person;
import ru.job4j.chat.models.Role;
import ru.job4j.chat.models.Room;
import ru.job4j.chat.repositories.MessageRepository;
import ru.job4j.chat.repositories.PersonRepository;
import ru.job4j.chat.repositories.RoomRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final PersonRepository personRepository;
    private final RoomRepository roomRepository;

    public MessageService(MessageRepository messageRepository,
                          PersonRepository personRepository,
                          RoomRepository roomRepository) {
        this.messageRepository = messageRepository;
        this.personRepository = personRepository;
        this.roomRepository = roomRepository;
    }

    public Set<Message> findAll() {
        return messageRepository.findAll();
    }

    public Optional<Message> findById(long id) {
        return messageRepository.findById(id);
    }

    public Message save(Message message) {
        return messageRepository.save(message);
    }

    public void update(Message message) {
        save(message);
    }

    public void delete(long id) {
        messageRepository.deleteById(id);
    }

    public List<Message> findByPersonId(Long personId) {
        return messageRepository.findByPersonId(personId);
    }

    public List<Message> findByRoomId(Long roomId) {
        return messageRepository.findByRoomId(roomId);
    }

    public Set<Message> findAll(Set<Long> ids) {
        return messageRepository.findAllById(ids);
    }

    @Transactional
    public void patchMessage(MessageDTO messageDTO) {
        var message = messageRepository
                .findById(messageDTO.getId())
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                "Message not found, id: " + messageDTO.getId()));
        if (messageDTO.getText() != null) {
            message.setText(messageDTO.getText());
        }
        if (messageDTO.getPersonID() != null) {
            Person person = personRepository.findById(messageDTO.getPersonID())
                    .orElseThrow(() -> new UsernameNotFoundException(
                    "Person not found, id: " + messageDTO.getPersonID()));
            message.setPerson(person);
        }
        if (messageDTO.getRoomID() != null) {
            Room room = roomRepository.findById(messageDTO.getRoomID())
                    .orElseThrow(() -> new UsernameNotFoundException(
                            "Room not found, id: " + messageDTO.getRoomID()));
            message.setRoom(room);
        }
    }
}
