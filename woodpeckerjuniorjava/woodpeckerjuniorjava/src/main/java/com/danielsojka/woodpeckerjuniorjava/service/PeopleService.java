package com.danielsojka.woodpeckerjuniorjava.service;

import com.danielsojka.woodpeckerjuniorjava.model.Person;
import com.danielsojka.woodpeckerjuniorjava.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;

@Service
public class PeopleService {

    @Autowired
    PersonRepository personRepository;

    public int findNumberOfPeople() {
        return personRepository.findAll().size();
    }

    public int findNumberOfPeopleAboveAge(int age) {
        return personRepository.findByAgeGreaterThan(age).size();
    }

    public Long findAverageAge() {
        return personRepository.findAverageAge();
    }

    public List<String> findDistinctHobbies() {
        List<Person> people = personRepository.findAll();
        List<String> hobbies = new ArrayList<>();
        if (isNotEmpty(people)) {
            people.forEach(person -> person.getHobby()
                    .stream()
                    .filter(hobby -> !hobbies.contains(hobby))
                    .forEach(hobbies::add));
        }
        return hobbies;
    }
}