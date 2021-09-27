package ru.job4j.chat.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import ru.job4j.chat.models.Person;
import ru.job4j.chat.models.Room;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PersonRepository extends CrudRepository<Person, Long> {
    Set<Person> findAll();

    Set<Person> findAllById(Iterable<Long> ids);

    @Query(value = "SELECT DISTINCT p FROM Person p JOIN FETCH p.roles r WHERE p.name = :name")
    Optional<Person> findByName(@Param("name") String name);
}
