package ru.job4j.chat.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.chat.models.Person;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person, Long> {
    List<Person> findAll();

    @Query(value = "SELECT DISTINCT p FROM Person p JOIN FETCH p.roles r WHERE p.name = :name")
    Optional<Person> findByName(@Param("name") String name);
}
