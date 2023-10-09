package com.mehmetberkan.SimpleSpringElasticsearchExample.repository;

import com.mehmetberkan.SimpleSpringElasticsearchExample.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends ElasticsearchRepository<Person, String> {
    @Query("{\"bool\": {\"must\": [{\"match\": {\"transactions.value\": \"?0\"}}]}}")
    Page<Person> findByTransactionsValueUsingCustomQuery(String transactionsValue, Pageable pageable);
}
