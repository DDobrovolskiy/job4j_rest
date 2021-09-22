package ru.job4j.chat.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.chat.models.Role;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Long> {
    List<Role> findAll();

    Optional<Role> findByTitle(String title);
}
