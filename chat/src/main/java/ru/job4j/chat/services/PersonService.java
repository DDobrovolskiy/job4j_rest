package ru.job4j.chat.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import ru.job4j.chat.dto.PersonDTO;
import ru.job4j.chat.models.Message;
import ru.job4j.chat.models.Person;
import ru.job4j.chat.models.Role;
import ru.job4j.chat.models.Room;
import ru.job4j.chat.repositories.MessageRepository;
import ru.job4j.chat.repositories.PersonRepository;
import ru.job4j.chat.repositories.RoleRepository;
import ru.job4j.chat.repositories.RoomRepository;
import ru.job4j.chat.validators.TargetValidated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.beans.Transient;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
@Validated
public class PersonService {

    private final PersonRepository personRepository;
    private final BCryptPasswordEncoder encoder;
    private final RoleRepository roleRepository;
    private final MessageRepository messageRepository;
    private final RoomRepository roomRepository;

    public PersonService(PersonRepository personRepository,
                         BCryptPasswordEncoder encoder,
                         RoleRepository roleRepository,
                         MessageRepository messageRepository,
                         RoomRepository roomRepository) {
        this.personRepository = personRepository;
        this.encoder = encoder;
        this.roleRepository = roleRepository;
        this.messageRepository = messageRepository;
        this.roomRepository = roomRepository;
    }

    public Set<Person> findAll() {
        return personRepository.findAll();
    }

    public Optional<Person> findById(@Positive long id) {
        return personRepository.findById(id);
    }

    @Transactional
    @Validated({TargetValidated.PersonCreate.class})
    public Person save(@Valid Person person) {
        log.debug("SAVE PERSON: {}", person);
        try {
            person.setPassword(encoder.encode(person.getPassword()));
            person.addRole(roleRepository.findByTitle("USER").orElseThrow());
            log.debug("Person success save {}", person);
            return personRepository.save(person);
        } catch (Exception e) {
            log.error("Error save Person: ", e);
            return person;
        }
    }

    @Validated({TargetValidated.PersonUpdate.class})
    public void update(@Valid Person person) {
        personRepository.save(person);
    }

    public void delete(@Positive long id) {
        personRepository.deleteById(id);
    }

    public Optional<Person> findByName(@NotBlank String name) {
        return personRepository.findByName(name);
    }

    public Set<Person> findAll(@NotEmpty Set<Long> ids) {
        return personRepository.findAllById(ids);
    }

    @Transactional
    public void patchPerson(PersonDTO personDTO) {
        var person = personRepository
                .findById(personDTO.getId())
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                "Person not found, id: " + personDTO.getId()));
        if (personDTO.getName() != null) {
            person.setName(personDTO.getName());
        }
        if (personDTO.getPassword() != null) {
            person.setPassword(personDTO.getPassword());
        }
        if (personDTO.getRolesID() != null) {
            Set<Role> roles = roleRepository.findAllById(personDTO.getRolesID());
            person.setRoles(roles);
        }
        if (personDTO.getMessagesID() != null) {
            Set<Message> messages = messageRepository.findAllById(personDTO.getMessagesID());
            person.setMessages(messages);
        }
        if (personDTO.getRoomsID() != null) {
            Set<Room> rooms = roomRepository.findAllById(personDTO.getRoomsID());
            person.setRooms(rooms);
        }
    }
}
