package ru.job4j.chat.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.chat.models.Person;
import ru.job4j.chat.models.Role;
import ru.job4j.chat.repositories.PersonRepository;

import java.beans.Transient;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PersonService {

    private final PersonRepository personRepository;
    private final BCryptPasswordEncoder encoder;
    private final RoleService roleService;

    public PersonService(PersonRepository personRepository,
                         BCryptPasswordEncoder encoder,
                         RoleService roleService) {
        this.personRepository = personRepository;
        this.encoder = encoder;
        this.roleService = roleService;
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Optional<Person> findById(long id) {
        return personRepository.findById(id);
    }

    @Transactional
    public Person save(Person person) {
        log.debug("SAVE PERSON: {}", person);
        try {
            person.setPassword(encoder.encode(person.getPassword()));
            person.addRole(roleService.findByTitle("USER").orElseThrow());
            log.debug("Person success save {}", person);
            return personRepository.save(person);
        } catch (Exception e) {
            log.error("Error save Person: ", e);
            return person;
        }
    }

    public void update(Person person) {
        save(person);
    }

    public void delete(long id) {
        personRepository.deleteById(id);
    }

    public Optional<Person> findByName(String name) {
        return personRepository.findByName(name);
    }
}
