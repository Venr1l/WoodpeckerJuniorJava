package com.danielsojka.woodpeckerjuniorjava.service;

import com.danielsojka.woodpeckerjuniorjava.configuration.RestConfigurationProperties;
import com.danielsojka.woodpeckerjuniorjava.model.Joke;
import com.danielsojka.woodpeckerjuniorjava.model.Person;
import com.danielsojka.woodpeckerjuniorjava.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@Service
public class JokeService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    RestConfigurationProperties restConfigurationProperties;

    @Autowired
    PersonRepository personRepository;

    public String getPersonalizedJoke(String firstName) {
        Optional<Person> optionalPerson = personRepository.findById(firstName);

        if (optionalPerson.isPresent()) {
            Joke joke = retrievePersonalizedJoke(optionalPerson.get());

            if (joke != null && joke.getValue() != null) {
                return joke.getValue().getJoke();
            }
        }
        return null;
    }

    private Joke retrievePersonalizedJoke(Person person) {
        String uri = UriComponentsBuilder.fromUriString(restConfigurationProperties.getIcnDb())
                .queryParam("firstName", person.getName())
                .queryParam("lastName", person.getAge())
                .toUriString();

        return restTemplate.getForObject(uri, Joke.class);
    }
}