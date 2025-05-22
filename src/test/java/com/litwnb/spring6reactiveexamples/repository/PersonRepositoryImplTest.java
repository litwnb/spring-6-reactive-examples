package com.litwnb.spring6reactiveexamples.repository;

import com.litwnb.spring6reactiveexamples.domain.Person;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;

class PersonRepositoryImplTest {
    PersonRepository repository = new PersonRepositoryImpl();

    @Test
    void testMonoByIdBlock() {
        Mono<Person> personMono = repository.getById(1);

        Person person = personMono.block();

        assert person != null;
        System.out.println(person);
    }

    @Test
    void testGetByIdSubscriber() {
        Mono<Person> personMono = repository.getById(1);

        personMono.subscribe(person -> System.out.println(person.toString()));
    }

    @Test
    void testMapOperation() {
        Mono<Person> personMono = repository.getById(1);

        personMono.map(Person::getFirstName)
                .subscribe(System.out::println);
    }
}