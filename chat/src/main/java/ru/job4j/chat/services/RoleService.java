package ru.job4j.chat.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.chat.models.Role;
import ru.job4j.chat.repositories.RoleRepository;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public List<Role> findAll() {
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
}
