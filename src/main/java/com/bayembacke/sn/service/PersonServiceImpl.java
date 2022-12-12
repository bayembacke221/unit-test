package com.bayembacke.sn.service;

import com.bayembacke.sn.model.Person;
import com.bayembacke.sn.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    PersonRepository personRepository;

    Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person createPerson(Person person) {
        logger.info("Person created");
        return personRepository.save(person);
    }

    @Override
    public Person updatePerson(Person person) {
        logger.info("Person updated");
        return personRepository.save(person);
    }

    @Override
    public void deletePerson(Person person) {
        logger.info("Person deleted");
        personRepository.delete(person);
    }

    @Override
    public Person getPerson(int id) {
        logger.info("Person found");
        Optional<Person> response =  personRepository.findById(id);
        return response.orElse(null);
    }

    @Override
    public List<Person> getAllPersons() {
        logger.info("All persons found");
        return personRepository.findAll();
    }
}
