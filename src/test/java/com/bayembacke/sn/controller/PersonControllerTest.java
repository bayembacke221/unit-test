package com.bayembacke.sn.controller;


import com.bayembacke.sn.model.Person;
import com.bayembacke.sn.service.PersonServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PersonController personController;

    @MockBean
    PersonServiceImpl personService;


    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void Hello(){
        String name= "tonux";
        String url = "http://localhost:" + port + "/api/person/hello?name="+name;
        String response = restTemplate.getForObject(url, String.class);
        assertEquals("Hello "+name, response);
    }

    @Test
    void getAllPerson() throws Exception {
        //Given
        RequestBuilder request = MockMvcRequestBuilders
                .get("/api/person")
                .contentType(MediaType.APPLICATION_JSON);

        //When
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();

        //Then
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void getPerson() throws Exception {
        Person person = new Person("malcolm","malcolmx221@gmail.com","778593165");
        person.setId(1);

        given(personService.getPerson(1)).willReturn(person);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/person/{id}",1)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);


        MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(person.getName()))).andReturn();


        MockHttpServletResponse response = result.getResponse();


        assertEquals(HttpStatus.OK.value(), response.getStatus());



    }

    @Test
    void createPerson()throws Exception {
        Person person = new Person("malcolm","malcolmx221@gmail.com","778593165");
        person.setId(1);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(person);

        given(personService.createPerson(any(Person.class))).willAnswer((invocation) ->
                invocation.getArgument(0));

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/person")
                .accept(MediaType.APPLICATION_JSON).content(json)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(jsonPath("$.name", is(person.getName()))).andReturn();


        MockHttpServletResponse response = result.getResponse();

        Person personSaved = objectMapper.readValue(response.getContentAsString(),Person.class);
        assertNotNull(personSaved);

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        assertEquals(personSaved.getName(),personSaved.getName());

    }

    @Test
    void updatePerson() throws Exception {
        Person person = new Person("malcolm","malcolmx221@gmail.com","778593165");
        person.setId(1);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(person);



        given(personService.getPerson(person.getId())).willReturn(person);

        given(personService.updatePerson(any(Person.class))).willAnswer((invocation) ->
                invocation.getArgument(0));

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/person/{id}",person.getId())
                .accept(MediaType.APPLICATION_JSON).content(json)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();

        MockHttpServletResponse response = result.getResponse();

        Person personSaved = objectMapper.readValue(response.getContentAsString(),Person.class);
        assertNotNull(personSaved);

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(personSaved.getName(),personSaved.getName());


    }

    @Test
    void deletePerson() throws Exception {
        Person person = new Person("malcolm","malcolmx221@gmail.com","778593165");
        person.setId(1);
        given(personService.getPerson(person.getId())).willReturn(person);
        doNothing().when(personService).deletePerson(person);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/person/{id}",person.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk()).andReturn();
        MockHttpServletResponse response = result.getResponse();


        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
}