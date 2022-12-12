package com.bayembacke.sn.repository;


import com.bayembacke.sn.model.Person;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class PersonRepositoryTest {

    @Autowired
    PersonRepository personRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void create(){
        Person person = personRepository.save(new Person("tonux", "tonux@gmail.com", "123456"));
        assertNotNull(person);
        assertEquals("tonux", person.getName());
    }

    @Test
    void update(){
        //Given
        Person person = personRepository.save(new Person("tonux", "tonux@gmail.com", "123456"));
        person.setName("Coundoul");
        //When
        Person personUpdated = personRepository.save(person);
        //Then
        assertNotNull(personUpdated);
        assertEquals("Coundoul", personUpdated.getName());
    }
    @Test
    public void getAllPerson(){
        List<Person> list = new ArrayList<Person>();
        list.add(new Person("tonux","tonuxndongo@gmail.com","289483931"));
        list.add(new Person("lahad","blm@gmail.com","4289358298"));
        list.add(new Person("baye","bayeserigne@gmail.com","989398493831"));
        personRepository.saveAll(list);

        List<Person> personList = personRepository.findAll();

        Assertions.assertThat(personList.size()).isGreaterThan(0);
    }
    @Test
    public void getPersonById(){
        Person personFind = personRepository.findById(2).get();
        assertEquals(2,personFind.getId());
    }
    @Test
    public void deletePerson(){
        //Person person  =  personRepository.save(new Person("mbacke","malcolmx221@gmail.com","778593165"));
        Person person = personRepository.findById(1).get();
        personRepository.delete(personRepository.findById(person.getId()).get());

        Person person1 = null;

        Optional<Person> person2 = personRepository.findByEmail(person.getEmail());

        if (person2.isPresent()){
            person1 = person2.get();
        }
        Assertions.assertThat(person1).isNull();
    }

}