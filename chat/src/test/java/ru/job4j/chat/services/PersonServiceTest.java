package ru.job4j.chat.services;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.job4j.chat.models.Person;
import ru.job4j.chat.models.Role;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PersonServiceTest {

    @Autowired
    private RoleService roleService;
    @Autowired
    private PersonService personService;
    private Role role;
    private Person person;

    @Before
    public void begin() {
        role = new Role();
        role.setTitle("USER");
        role = roleService.save(role);
        log.debug("Role: {}", role);
        log.debug("Role search: {}", roleService.findByTitle("USER").orElseThrow());
        person = new Person();
        person.setName("name");
        person.setPassword("password");
        person = personService.save(person);
    }

    @Test
    public void findByName() {
        var personFind = personService.findByName("name");
        System.out.println(personFind.get().getRoles());
        Assert.assertThat(personFind.get().getRoles().get(0), is(role));
    }
}