package ru.job4j.chat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.chat.dto.PersonDTO;
import ru.job4j.chat.models.Person;
import ru.job4j.chat.models.Role;
import ru.job4j.chat.services.PersonService;
import ru.job4j.chat.validators.TargetValidated;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.Set;

@Validated
@RestController
@RequestMapping("/persons")
public class PersonControl {

    private final PersonService personService;

    public PersonControl(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping()
    public Set<Person> findAll() {
        return personService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> findById(@PathVariable @Positive long id) {
        var person = personService.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Person not found"
        ));
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @PostMapping()
    @Validated({TargetValidated.PersonCreate.class})
    public ResponseEntity<Person> save(@RequestBody @Valid Person person) {
        return new ResponseEntity<>(personService.save(person), HttpStatus.CREATED);
    }

    @PutMapping()
    @Validated({TargetValidated.PersonUpdate.class})
    public ResponseEntity<Void> update(@RequestBody @Valid Person person) {
        personService.update(person);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @Positive long id) {
        personService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping()
    public ResponseEntity<Void> patch(@RequestBody PersonDTO personDTO) {
        personService.patchPerson(personDTO);
        return ResponseEntity.ok().build();
    }
}
