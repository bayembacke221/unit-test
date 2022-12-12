package com.bayembacke.sn.controller;

import com.bayembacke.sn.model.Person;
import com.bayembacke.sn.service.PersonServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    /**
     * - GET /api/person
     * - GET /api/person/{id}
     * - POST /api/person
     * - PUT /api/person/{id}
     * - DELETE /api/person/{id}
     */

    PersonServiceImpl personService;

    public PersonController(PersonServiceImpl personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<List<Person>> getAllPerson() {
        return ResponseEntity.ok(personService.getAllPersons());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPerson(@RequestParam int id) {
        return ResponseEntity.ok(personService.getPerson(id));
    }

    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        return ResponseEntity.ok(personService.createPerson(person));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@RequestBody Person person, @RequestParam int id) {
        Person personToUpdate = personService.getPerson(id);
        if(personToUpdate == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(personService.updatePerson(person));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Person> deletePerson(@RequestParam int id) {
        personService.deletePerson(personService.getPerson(id));
        return ResponseEntity.ok().build();
    }


    @GetMapping("/hello")
    public String hello(@RequestParam String name) {
        return "Hello "+name;
    }

}
