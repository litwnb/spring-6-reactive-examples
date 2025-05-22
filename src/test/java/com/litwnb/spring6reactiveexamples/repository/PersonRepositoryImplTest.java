package com.litwnb.spring6reactiveexamples.repository;

import com.litwnb.spring6reactiveexamples.domain.Person;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

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

    @Test
    void testFluxBlockFirst() {
        Flux<Person> personFlux = repository.findAll();

        Person person = personFlux.blockFirst();

        System.out.println(person.toString());
    }

    @Test
    void testFluxSubscriber() {
        Flux<Person> personFlux = repository.findAll();

        personFlux.subscribe(person -> System.out.println(person.toString()));
    }

    @Test
    void testFluxMap() {
        Flux<Person> personFlux = repository.findAll();

        personFlux.map(Person::getFirstName).subscribe(System.out::println);
    }

    @Test
    void testFluxToList() {
        Flux<Person> personFlux = repository.findAll();

        Mono<List<Person>> listMono = personFlux.collectList();

        listMono.subscribe(list ->
                list.forEach(person -> System.out.println(person.getFirstName())));
    }

    @Test
    void testFilterOnName() {
        repository.findAll()
                .filter(person -> person.getFirstName().equals("Pam"))
                .subscribe(person -> System.out.println(person.getFirstName()));
    }

    @Test
    void testGetById() {
        Mono<Person> pamMono = repository.findAll()
                .filter(person -> person.getFirstName().equals("Pam")).next();

        pamMono.subscribe(person -> System.out.println(person.getFirstName()));
    }
}