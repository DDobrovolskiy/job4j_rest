package ru.job4j.auth.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.auth.domain.Person;
import ru.job4j.auth.services.PersonService;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerTest {

    @Autowired
    private PersonController personController;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PersonService personService;

    @Test
    public void findAll() throws Exception {
        when(personService.findAll())
                .thenReturn(List.of(
                        new Person(1, "login", "password")));
        mockMvc.perform(get("/persons/"))
                .andDo(print())
                .andExpect(status().isOk());
        verify(personService).findAll();
    }

    @Test
    public void findByIdFound() throws Exception {
        Person person = new Person(1, "login", "password");
        when(personService.findById(1))
                .thenReturn(Optional.of(person));
        var result = mockMvc.perform(get("/persons/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        var personJSON = result.getResponse().getContentAsString();
        Assert.assertThat(personJSON, is(new ObjectMapper().writeValueAsString(person)));
        verify(personService).findById(1);
    }

    @Test
    public void findByIdNotFound() throws Exception {
        Person person = new Person();
        when(personService.findById(2))
                .thenReturn(Optional.empty());
        var result = mockMvc.perform(get("/persons/2"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andReturn();
        var personJSON = result.getResponse().getContentAsString();
        Assert.assertThat(personJSON, is(new ObjectMapper().writeValueAsString(person)));
        verify(personService).findById(2);
    }

    @Test
    public void create() throws Exception {
        String login = "login";
        String password = "password";
        Person person = new Person(1, login, password);
        Person personNew = new Person(0, login, password);
        when(personService.create(personNew))
                .thenReturn(person);
        var result = mockMvc.perform(post("/persons/")
                            .content(
                                    new ObjectMapper().writeValueAsString(personNew))
                            .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();
        var personJSON = result.getResponse().getContentAsString();
        Assert.assertThat(personJSON, is(new ObjectMapper().writeValueAsString(person)));
        verify(personService).create(any());
    }

    @Test
    public void update() throws Exception {
        String login = "login";
        String password = "password";
        Person person = new Person(3, login, password);
        mockMvc.perform(put("/persons/")
                .content(
                        new ObjectMapper().writeValueAsString(person))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        verify(personService).update(person);
    }

    @Test
    public void deleteWithId() throws Exception {
        mockMvc.perform(delete("/persons/4"))
                .andDo(print())
                .andExpect(status().isOk());
        verify(personService).delete(4);
    }
}