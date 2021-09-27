package ru.job4j.chat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.chat.dto.PersonDTO;
import ru.job4j.chat.models.Person;
import ru.job4j.chat.models.Role;
import ru.job4j.chat.services.PersonService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/persons")
public class PersonControl {
    @Autowired
    private PersonService personService;

    @GetMapping()
    public Set<Person> findAll() {
        return personService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> findById(@PathVariable long id) {
        var person = personService.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Person not found"
        ));
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Person> save(@RequestBody Person person) {
        return new ResponseEntity<>(personService.save(person), HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<Void> update(@RequestBody Person person) {
        personService.update(person);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        personService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping()
    public ResponseEntity<Void> patch(@RequestBody PersonDTO personDTO) {
        personService.patchPerson(personDTO);
        return ResponseEntity.ok().build();
    }
}
