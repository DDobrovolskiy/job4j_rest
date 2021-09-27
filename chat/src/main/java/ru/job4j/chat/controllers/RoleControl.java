package ru.job4j.chat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.chat.dto.PersonDTO;
import ru.job4j.chat.dto.RoleDTO;
import ru.job4j.chat.models.Role;
import ru.job4j.chat.services.RoleService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/roles")
public class RoleControl {

    @Autowired
    private RoleService roleService;

    @GetMapping()
    public Set<Role> findAll() {
        return roleService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> findById(@PathVariable long id) {
        var role = roleService.findById(id);
        return new ResponseEntity<>(
                role.orElse(new Role()),
                role.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }

    @PostMapping()
    public ResponseEntity<Role> save(@RequestBody Role role) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("MyHead", "Head")
                .contentType(MediaType.APPLICATION_JSON)
                .body(roleService.save(role));
    }

    @PutMapping()
    public ResponseEntity<Void> update(Role role) {
        roleService.update(role);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        roleService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping()
    public ResponseEntity<Void> patch(@RequestBody RoleDTO roleDTO) {
        roleService.patchRole(roleDTO);
        return ResponseEntity.ok().build();
    }
}
