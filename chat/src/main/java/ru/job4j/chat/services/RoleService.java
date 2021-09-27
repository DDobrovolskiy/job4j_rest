package ru.job4j.chat.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.chat.dto.RoleDTO;
import ru.job4j.chat.models.Message;
import ru.job4j.chat.models.Person;
import ru.job4j.chat.models.Role;
import ru.job4j.chat.models.Room;
import ru.job4j.chat.repositories.PersonRepository;
import ru.job4j.chat.repositories.RoleRepository;

import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class RoleService {

    private final RoleRepository roleRepository;
    private final PersonRepository personRepository;

    public RoleService(RoleRepository roleRepository,
                       PersonRepository personRepository) {
        this.roleRepository = roleRepository;
        this.personRepository = personRepository;
    }

    public Set<Role> findAll() {
        return roleRepository.findAll();
    }

    public Optional<Role> findById(long id) {
        return roleRepository.findById(id);
    }

    public Role save(Role role) {
        try {
            return roleRepository.save(role);
        } catch (Exception e) {
            log.error("Error", e);
            return role;
        }
    }

    public void update(Role role) {
        save(role);
    }

    public void delete(long id) {
        roleRepository.deleteById(id);
    }

    public Optional<Role> findByTitle(String title) {
        return roleRepository.findByTitle(title);
    }

    public Set<Role> findAll(Set<Long> ids) {
        return roleRepository.findAllById(ids);
    }

    @Transactional
    public void patchRole(RoleDTO roleDTO) {
        var role = roleRepository
                .findById(roleDTO.getId())
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                "Role not found, id: " + roleDTO.getId()));
        if (roleDTO.getTitle() != null) {
            role.setTitle(roleDTO.getTitle());
        }
        if (roleDTO.getPersonsID() != null) {
            Set<Person> persons = personRepository.findAllById(roleDTO.getPersonsID());
            role.setPersons(persons);
        }
    }
}
