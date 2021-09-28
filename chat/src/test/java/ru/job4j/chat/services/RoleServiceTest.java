package ru.job4j.chat.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.job4j.chat.models.Role;
import ru.job4j.chat.repositories.PersonRepository;
import ru.job4j.chat.repositories.RoleRepository;

import javax.validation.ConstraintViolationException;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleServiceTest {

    @Autowired
    private RoleService roleService;

    @Test(expected = ConstraintViolationException.class)
    public void findById() {
        long testId = -1;
        roleService.findById(testId);
    }

    @Test(expected = ConstraintViolationException.class)
    public void save() {
        Role role = new Role();
        role.setId(5);
        role.setTitle("Test");
        roleService.save(role);
    }

    @Test(expected = ConstraintViolationException.class)
    public void update() {
        Role role = new Role();
        role.setId(0);
        role.setTitle("Test");
        roleService.update(role);
    }

    @Test(expected = ConstraintViolationException.class)
    public void delete() {
        long testId = -1;
        roleService.delete(testId);
    }

    @Test(expected = ConstraintViolationException.class)
    public void findByTitleNull() {
        roleService.findByTitle(null);
    }

    @Test(expected = ConstraintViolationException.class)
    public void findByTitleBlank() {
        roleService.findByTitle("");
    }

    @Test(expected = ConstraintViolationException.class)
    public void findAllEmpty() {
        Set<Long> set = Set.of();
        roleService.findAll(set);
    }

    @Test(expected = ConstraintViolationException.class)
    public void findAllNull() {
        roleService.findAll(null);
    }
}