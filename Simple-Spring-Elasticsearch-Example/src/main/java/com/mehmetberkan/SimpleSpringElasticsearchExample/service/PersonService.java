package com.mehmetberkan.SimpleSpringElasticsearchExample.service;

import com.mehmetberkan.SimpleSpringElasticsearchExample.model.Person;
import com.mehmetberkan.SimpleSpringElasticsearchExample.repository.PersonRepository;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.Operator;
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

import static org.elasticsearch.index.query.QueryBuilders.matchPhraseQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final ElasticsearchOperations elasticsearchOperations;
    private final String matchQueryConstant = "description";
    private final String indexNameConstant = "persons";

    public PersonService(PersonRepository personRepository, ElasticsearchOperations elasticsearchOperations) {
        this.personRepository = personRepository;
        this.elasticsearchOperations = elasticsearchOperations;
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

    public List<SearchHit<Person>> getPersonsByDescription(String search) {

        NativeSearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(matchQuery(indexNameConstant, search))
                .build();

        return elasticsearchOperations.search(query, Person.class,
                IndexCoordinates.of()).getSearchHits();
    }


    public List<SearchHit<Person>> getPersonsByScore(String searchTerm) {
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchQuery(matchQueryConstant, searchTerm)
                        .minimumShouldMatch("100%"))
                .build();
        return elasticsearchOperations.search(searchQuery, Person.class,
                IndexCoordinates.of(indexNameConstant)).getSearchHits();
    }

    public List<SearchHit<Person>> getPersonsByFullText(String searchTerm) {
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchQuery(matchQueryConstant, searchTerm)
                        .operator(Operator.AND))
                .build();

        return elasticsearchOperations.search(searchQuery, Person.class,
                IndexCoordinates.of(indexNameConstant)).getSearchHits();
    }

    public List<SearchHit<Person>> getPersonByFuzziness(String searchTerm) {
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchQuery(matchQueryConstant, searchTerm)
                        .operator(Operator.AND)
                        .fuzziness(Fuzziness.ONE)
                        .prefixLength(2))
                .build();

        return elasticsearchOperations.search(searchQuery, Person.class,
                IndexCoordinates.of(indexNameConstant)).getSearchHits();
    }

    public List<SearchHit<Person>> getPersonsByPhrase(String searchTerm) {
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchPhraseQuery(matchQueryConstant, searchTerm).slop(1))
                .build();

        return elasticsearchOperations.search(searchQuery, Person.class,
                IndexCoordinates.of(indexNameConstant)).getSearchHits();
    }
}
