package ru.job4j.chat.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.chat.models.Person;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Long> {
    List<Person> findAll();
}
