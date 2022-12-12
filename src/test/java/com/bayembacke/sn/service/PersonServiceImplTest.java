package com.bayembacke.sn.service;


import com.bayembacke.sn.model.Person;
import com.bayembacke.sn.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class PersonServiceImplTest {

    @Mock
    PersonRepository personRepository;

    @InjectMocks
    PersonServiceImpl personService;

    @Test
    void createPerson() {
        //Given
        Person person = new Person("Moussa", "tonux@gmail.com", "123456");
        person.setId(1);
        when(personRepository.save(any())).thenReturn(person);

        //When
        Person personResponse = personService.createPerson(person);

        //Then
        assertEquals("Moussa", personResponse.getName());
        assertEquals(1, personResponse.getId());
        verify(personRepository, atLeastOnce()).save(any());
    }

    @Test
    void updatePerson() {
        //Given
        Person person = new Person("malcolm","malcolmx221@gmail.com","778593165");
        person.setId(1);
        person.setName("moustapha");
        when(personRepository.save(any())).thenReturn(person);

        //When
        Person personAded =personService.updatePerson(person);
        //Then
        assertEquals("moustapha",personAded.getName());
        assertEquals(1,personAded.getId());
        verify(personRepository,atLeastOnce()).save(any());
    }

    @Test
    void deletePerson() {
        //Given
        Person person = new Person("malcolm","malcolmx221@gmail.com","778593165");
        person.setId(1);
        doNothing().when(personRepository).delete(any());

        //When
        personService.deletePerson(person);

        //Then
        verify(personRepository, atLeastOnce()).delete(any());
        verifyNoMoreInteractions(personRepository);    }

    @Test
    void getPerson() {
        //Given
        Person newPerson = new Person("tonux", "tonuxndongo@gmail.com", "289483931");
        newPerson.setId(1);
        when(personRepository.findById(1)).thenReturn(Optional.of(newPerson));
        //When
        Person person = personService.getPerson(1);
        //Then
        assertEquals("tonux", person.getName());
        assertEquals(1, person.getId());
        assertEquals("tonuxndongo@gmail.com", person.getEmail());
    }

    @Test
    void getAllPersons() {
        //Given
        List<Person> list = new ArrayList<Person>();
        list.add(new Person("tonux","tonuxndongo@gmail.com","289483931"));
        list.add(new Person("lahad","blm@gmail.com","4289358298"));
        list.add(new Person("baye","bayeserigne@gmail.com","989398493831"));
        when(personRepository.findAll()).thenReturn(list);
        //When
        List<Person> personList = personService.getAllPersons();
        //Then
        assertEquals(3, personList.size());
        verify(personRepository, atLeastOnce()).findAll();
    }

}