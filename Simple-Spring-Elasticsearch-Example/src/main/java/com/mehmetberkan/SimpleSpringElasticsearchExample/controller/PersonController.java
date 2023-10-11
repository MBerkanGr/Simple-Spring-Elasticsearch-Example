package com.mehmetberkan.SimpleSpringElasticsearchExample.controller;

import com.mehmetberkan.SimpleSpringElasticsearchExample.model.Person;
import com.mehmetberkan.SimpleSpringElasticsearchExample.service.PersonService;
import org.springframework.data.elasticsearch.core.SearchHit;
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

    @GetMapping("/search")
    public ResponseEntity<List<SearchHit<Person>>> search(@RequestParam String search) {
        return ResponseEntity.ok(personService.getPersonsByDescription(search));
    }

    @GetMapping("/search-score")
    public ResponseEntity<List<SearchHit<Person>>> searchByScoreDescription(@RequestParam String search) {
        return ResponseEntity.ok(personService.getPersonsByScore(search));
    }

    @GetMapping("/search-fulltext")
    public ResponseEntity<List<SearchHit<Person>>> searchByFullText(@RequestParam String search) {
        return ResponseEntity.ok(personService.getPersonsByFullText(search));
    }

    @GetMapping("/search-fuzziness")
    public ResponseEntity<List<SearchHit<Person>>> searchByFuzziness(@RequestParam String search) {
        return ResponseEntity.ok(personService.getPersonByFuzziness(search));
    }

    @GetMapping("/search-phrase")
    public ResponseEntity<List<SearchHit<Person>>> searchByPhrase(@RequestParam String search) {
        return ResponseEntity.ok(personService.getPersonsByPhrase(search));
    }
}
