package com.mehmetberkan.SimpleSpringElasticsearchExample.service;

import com.mehmetberkan.SimpleSpringElasticsearchExample.model.Person;
import com.mehmetberkan.SimpleSpringElasticsearchExample.repository.PersonRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person addPerson(Person person) {
        return personRepository.save(person);
    }

    public List<Person> getAll() {

        List<Person> personList = new ArrayList<>();

        for (Person person : personRepository.findAll()) {
            personList.add(person);
        }

        return personList;
    }

    public List<Person> getByTransactionValue(String transactionValue) {
        Page<Person> personPage = personRepository
                .findByTransactionsValueUsingCustomQuery(transactionValue, PageRequest.of(0, 10));

        return personPage.getContent();
    }
}
