package com.mehmetberkan.SimpleSpringElasticsearchExample.controller;

import com.mehmetberkan.SimpleSpringElasticsearchExample.model.Person;
import com.mehmetberkan.SimpleSpringElasticsearchExample.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/persons")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public ResponseEntity<Person> addPerson(@RequestBody Person person) {
        return ResponseEntity.ok(personService.addPerson(person));
    }

    @GetMapping
    public ResponseEntity<List<Person>> getAll() {
        return ResponseEntity.ok(personService.getAll());
    }

    @GetMapping("/transactions/{transactionValue}")
    public ResponseEntity<List<Person>> getByTransactionValue(@PathVariable String transactionValue) {
        return ResponseEntity.ok(personService.getByTransactionValue(transactionValue));
    }
}
