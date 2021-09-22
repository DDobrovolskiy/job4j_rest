package ru.job4j.chat.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.chat.models.Role;
import ru.job4j.chat.repositories.RoleRepository;

import java.util.List;
import java.util.Optional;

@Service
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
        return roleRepository.save(role);
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
