package com.danielsojka.woodpeckerjuniorjava.repository;

import com.danielsojka.woodpeckerjuniorjava.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, String> {

    List<Person> findByAgeGreaterThan(int value);

    @Query("SELECT AVG(p.age) FROM Person p")
    Long findAverageAge();
}