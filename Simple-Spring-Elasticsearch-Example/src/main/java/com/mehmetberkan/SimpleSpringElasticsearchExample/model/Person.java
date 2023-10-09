package com.mehmetberkan.SimpleSpringElasticsearchExample.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.xml.crypto.Data;
import java.util.List;

@Document(indexName = "persons")
public class Person {
    @Id
    private String id;
    @Field(type = FieldType.Text)
    private String name;
    @Field(type = FieldType.Text)
    private String surname;
    @Field(type = FieldType.Text)
    private String address;
    @Field(type = FieldType.Date)
    private Data birthDate;
    @Field(type = FieldType.Text)
    private String description;
    @Field(type = FieldType.Nested, includeInParent = true)
    private List<Transaction> transactions;

    public Person() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Data getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Data birthDate) {
        this.birthDate = birthDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

}

