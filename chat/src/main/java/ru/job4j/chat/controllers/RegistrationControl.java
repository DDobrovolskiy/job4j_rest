package ru.job4j.chat.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.chat.models.Person;
import ru.job4j.chat.services.PersonService;

@RestController
@RequestMapping("/reg")
public class RegistrationControl {
    private final PersonService personService;

    public RegistrationControl(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping()
    public ResponseEntity<Person> reg(@RequestBody Person person) {
        return new ResponseEntity<>(personService.save(person), HttpStatus.CREATED);
    }
}
