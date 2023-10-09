package com.mehmetberkan.SimpleSpringElasticsearchExample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
@EnableElasticsearchRepositories(basePackages = "com.mehmetberkan.SimpleSpringElasticsearchExample.repository")
public class SimpleSpringElasticsearchExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleSpringElasticsearchExampleApplication.class, args);
	}

}
