package com.danielsojka.woodpeckerjuniorjava.service;

import com.danielsojka.woodpeckerjuniorjava.configuration.CSVConfigurationProperties;
import com.danielsojka.woodpeckerjuniorjava.constants.HobbyConstants;
import com.danielsojka.woodpeckerjuniorjava.model.Person;
import com.danielsojka.woodpeckerjuniorjava.model.PersonCSV;
import com.danielsojka.woodpeckerjuniorjava.repository.PersonRepository;
import com.opencsv.bean.CsvToBeanBuilder;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;

@Service
public class PersonCSVService {

    @Autowired
    CSVConfigurationProperties csvConfigurationProperties;

    @Autowired
    PersonRepository personRepository;

    public void initPeopleData() {
        List<PersonCSV> personList = loadPersonListFromCsvFile();
        if (isNotEmpty(personList)) {
            List<PersonCSV> validatedPersonCSVList = getValidatedPersonCSVList(personList);

            if (isNotEmpty(validatedPersonCSVList)) {
                savePeople(validatedPersonCSVList);
            }
        }
    }

    private List<PersonCSV> loadPersonListFromCsvFile() {
        try {
            return (List<PersonCSV>) new CsvToBeanBuilder(getPersonDbFileReader())
                    .withType(PersonCSV.class)
                    .build()
                    .parse();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private FileReader getPersonDbFileReader() throws FileNotFoundException {
        File personDbFile = ResourceUtils.getFile(csvConfigurationProperties.getPersonDb());
        return new FileReader(personDbFile);
    }

    public List<PersonCSV> getValidatedPersonCSVList(@NotNull List<PersonCSV> personList) {
        return personList.stream()
                .filter(person -> person.getAge() != null)
                .filter(person -> HobbyConstants.AVAILABLE_HOBBIES.contains(person.getHobby()))
                .collect(Collectors.toList());
    }

    private void savePeople(@NotNull List<PersonCSV> validatedPersonCSVList) {
        for (PersonCSV personCSV : validatedPersonCSVList) {
            if (isPersonNotInRepository(personCSV)) {
                Person person = new Person();
                person.setName(personCSV.getName());
                person.setAge(personCSV.getAge());
                person.setHobby(findDuplicatedPersonHobbies(validatedPersonCSVList, personCSV));
                personRepository.save(person);
            }
        }
    }

    private boolean isPersonNotInRepository(PersonCSV personCSV) {
        return !personRepository.findById(personCSV.getName()).isPresent();
    }

    public List<String> findDuplicatedPersonHobbies(@NotNull List<PersonCSV> personCSVList, @NotNull PersonCSV personCSV) {
        return personCSVList.stream()
                .filter(csvPerson -> csvPerson.getName().equals(personCSV.getName()))
                .collect(Collectors.toList())
                .stream()
                .map(PersonCSV::getHobby)
                .distinct()
                .collect(Collectors.toList());
    }
}