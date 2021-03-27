package com.danielsojka.woodpeckerjuniorjava;

import com.danielsojka.woodpeckerjuniorjava.model.PersonCSV;
import com.danielsojka.woodpeckerjuniorjava.service.PersonCSVService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.danielsojka.woodpeckerjuniorjava.constants.HobbyConstants.GAMING;
import static com.danielsojka.woodpeckerjuniorjava.constants.HobbyConstants.MUSIC;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class PersonCSVServiceValidationsTests {

    @Autowired
    PersonCSVService personCSVService;

    List<PersonCSV> personCSVList;
    List<PersonCSV> validatedPersonCSVList;
    String testName = "TestName";

    @Test
    void personWithInvalidHobbyIgnored() {
        personCSVList = Collections.singletonList(createPerson(testName, 20, "Invalid hobby"));

        validatedPersonCSVList = personCSVService.getValidatedPersonCSVList(personCSVList);

        assertTrue(validatedPersonCSVList.isEmpty());
    }

    @Test
    void personWithValidHobbyNotIgnored() {
        personCSVList = Collections.singletonList(createPerson(testName, 20, MUSIC));

        validatedPersonCSVList = personCSVService.getValidatedPersonCSVList(personCSVList);

        assertFalse(validatedPersonCSVList.isEmpty());
    }

    @Test
    void personWithoutAgeIgnored() {
        personCSVList = Collections.singletonList(createPerson(testName, null, GAMING));

        validatedPersonCSVList = personCSVService.getValidatedPersonCSVList(personCSVList);

        assertTrue(validatedPersonCSVList.isEmpty());
    }

    @Test
    void personWithAgeNotIgnored() {
        personCSVList = Collections.singletonList(createPerson(testName, 20, GAMING));

        validatedPersonCSVList = personCSVService.getValidatedPersonCSVList(personCSVList);

        assertFalse(validatedPersonCSVList.isEmpty());
    }

    @Test
    void findDuplicatedPersonHobbiesReturnsListWithTwoHobbiesWhenNameIsMatching() {
        PersonCSV personCSV = createPerson(testName, 20, MUSIC);
        personCSVList = Arrays.asList(createPerson(testName, 20, GAMING), personCSV);
        List<String> expectedList = Arrays.asList(GAMING, MUSIC);

        List<String> duplicatedPersonHobbies = personCSVService.findDuplicatedPersonHobbies(personCSVList, personCSV);

        assertTrue(duplicatedPersonHobbies.size() == 2 && duplicatedPersonHobbies.containsAll(expectedList));
    }

    @Test
    void findDuplicatedPersonHobbiesReturnsListWithOneHobbyWhenNameIsMatchingAndHobbiesAreMatching() {
        PersonCSV personCSV = createPerson(testName, 20, GAMING);
        personCSVList = Arrays.asList(createPerson(testName, 20, GAMING), personCSV);

        List<String> duplicatedPersonHobbies = personCSVService.findDuplicatedPersonHobbies(personCSVList, personCSV);

        assertTrue(duplicatedPersonHobbies.size() == 1 && duplicatedPersonHobbies.contains(GAMING));
    }

    PersonCSV createPerson(String name, Integer age, String hobby) {
        PersonCSV personCSV = new PersonCSV();
        personCSV.setName(name);
        personCSV.setAge(age);
        personCSV.setHobby(hobby);
        return personCSV;
    }
}
