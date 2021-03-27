package com.danielsojka.woodpeckerjuniorjava.controller;

import com.danielsojka.woodpeckerjuniorjava.service.JokeService;
import com.danielsojka.woodpeckerjuniorjava.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import java.util.List;

@RestController
@RequestMapping("/people")
public class PersonController {

    @Autowired
    JokeService jokeService;

    @Autowired
    PeopleService peopleService;

    @GetMapping("/joke")
    public ResponseEntity<String> getPersonalizedJoke(@RequestParam String name) {
        String personalizedJoke;

        try {
            personalizedJoke = jokeService.getPersonalizedJoke(name);
        } catch (RestClientException e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
        }

        if (personalizedJoke != null) {
            return ResponseEntity.ok(personalizedJoke);
        } else return ResponseEntity.notFound().build();
    }

    @GetMapping("/count")
    public int getNumberOfPeople() {
        return peopleService.findNumberOfPeople();
    }

    @GetMapping("/count/above")
    public int getNumberOfPeopleAboveAge(@RequestParam int age) {
        return peopleService.findNumberOfPeopleAboveAge(age);
    }

    @GetMapping("/count/avg")
    public Long getAverageAge() {
        Long averageAge = peopleService.findAverageAge();
        if (averageAge != null) {
            return averageAge;
        } else return 0L;
    }

    @GetMapping("/hobbies")
    public List<String> getPeopleHobbies() {
        return peopleService.findDistinctHobbies();
    }
}