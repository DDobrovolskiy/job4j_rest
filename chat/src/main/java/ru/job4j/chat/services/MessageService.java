package ru.job4j.chat.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.chat.models.Message;
import ru.job4j.chat.repositories.MessageRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public List<Message> findAll() {
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
}
