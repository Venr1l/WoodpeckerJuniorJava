package com.danielsojka.woodpeckerjuniorjava.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "csv")
public class CSVConfigurationProperties {

    private String personDb;

    public String getPersonDb() {
        return personDb;
    }

    public void setPersonDb(String personDb) {
        this.personDb = personDb;
    }
}