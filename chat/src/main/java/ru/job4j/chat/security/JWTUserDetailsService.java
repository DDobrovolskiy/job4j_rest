package ru.job4j.chat.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.job4j.chat.services.PersonService;

@Service
@Slf4j
public class JWTUserDetailsService implements UserDetailsService {
    private final PersonService personService;

    public JWTUserDetailsService(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        var person = personService.findByName(name).orElseThrow(
                () -> new UsernameNotFoundException("Person not found name: " + name));

        return person;
    }
}
