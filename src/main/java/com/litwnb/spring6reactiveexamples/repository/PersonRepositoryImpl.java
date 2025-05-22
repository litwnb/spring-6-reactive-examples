package com.litwnb.spring6reactiveexamples.repository;

import com.litwnb.spring6reactiveexamples.domain.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class PersonRepositoryImpl implements PersonRepository {
    Person michael = Person.builder().id(1).firstName("Michael").lastName("Scott").build();
    Person pam = Person.builder().id(2).firstName("Pam").lastName("Beesly").build();
    Person jim = Person.builder().id(3).firstName("Jim").lastName("Halpert").build();
    Person dwight = Person.builder().id(4).firstName("Dwight").lastName("Schrute").build();

    @Override
    public Mono<Person> getById(Integer id) {
        return Mono.just(michael);
    }

    @Override
    public Flux<Person> findAll() {
        return Flux.just(michael, pam, jim, dwight);
    }
}
