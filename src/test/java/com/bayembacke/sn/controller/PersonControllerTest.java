package com.bayembacke.sn.controller;


import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PersonController personController;

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

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
    void getPerson() {
        // TODO : add test getPerson
    }

    @Test
    void createPerson() {
        // TODO : add test createPerson
    }

    @Test
    void updatePerson() {
        // TODO : add test updatePerson
    }

    @Test
    void deletePerson() {
        // TODO : add test deletePerson
    }
}