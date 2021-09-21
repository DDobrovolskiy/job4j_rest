package ru.job4j.auth.services;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import ru.job4j.auth.domain.Person;
import ru.job4j.auth.repository.PersonRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class PersonService {
    private final PersonRepository persons;

    public PersonService(final PersonRepository persons) {
        this.persons = persons;
    }

    public List<Person> findAll() {
        return StreamSupport.stream(
                this.persons.findAll().spliterator(), false
        ).collect(Collectors.toList());
    }

    public Optional<Person> findById(long id) {
        return this.persons.findById(id);
    }

    public Person create(@NonNull Person person) {
        log.debug("create: {}", person);
        return this.persons.save(person);
    }

    public Person update(@NonNull Person person) {
        return create(person);
    }

    public void delete(@PathVariable long id) {
        this.persons.deleteById(id);
    }

    public List<Person> findByEmployee(long idEmployee) {
        return this.persons.findByEmployeeId(idEmployee);
    }
}
