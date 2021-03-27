package com.danielsojka.woodpeckerjuniorjava.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "api")
public class RestConfigurationProperties {

    private String icnDb;

    public String getIcnDb() {
        return icnDb;
    }

    public void setIcnDb(String icnDb) {
        this.icnDb = icnDb;
    }
}