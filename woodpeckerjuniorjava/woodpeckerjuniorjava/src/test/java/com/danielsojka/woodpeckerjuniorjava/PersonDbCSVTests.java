package com.danielsojka.woodpeckerjuniorjava;

import com.danielsojka.woodpeckerjuniorjava.configuration.CSVConfigurationProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class PersonDbCSVTests {

    @Autowired
    CSVConfigurationProperties csvConfigurationProperties;

    @Test
    void personDBCsvFileExists() throws FileNotFoundException {
        assertTrue(ResourceUtils.getFile(csvConfigurationProperties.getPersonDb()).exists());
    }
}
