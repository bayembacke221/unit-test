package com.bayembacke.sn.service;

import com.bayembacke.sn.model.Person;

import java.util.List;

public interface PersonService {

    Person createPerson(Person person);
    Person updatePerson(Person person);
    void deletePerson(Person person);
    Person getPerson(int id);
    List<Person> getAllPersons();
}
