package ru.job4j.chat.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.chat.models.Person;
import ru.job4j.chat.repositories.PersonRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Optional<Person> findById(long id) {
        return personRepository.findById(id);
    }

    public Person save(Person person) {
        return personRepository.save(person);
    }

    public void update(Person person) {
        save(person);
    }

    public void delete(long id) {
        personRepository.deleteById(id);
    }
}
