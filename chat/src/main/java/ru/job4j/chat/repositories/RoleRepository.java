package ru.job4j.chat.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.chat.models.Role;
import ru.job4j.chat.models.Room;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Set<Role> findAll();

    Set<Role> findAllById(Iterable<Long> ids);

    Optional<Role> findByTitle(String title);
}
