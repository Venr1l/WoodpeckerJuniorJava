package com.danielsojka.woodpeckerjuniorjava.model;

import java.util.HashMap;
import java.util.Map;

public class Joke {

    private String type;
    private JokeValue value;
    private final Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public JokeValue getValue() {
        return value;
    }

    public void setValue(JokeValue value) {
        this.value = value;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return "Joke{" +
                "type='" + type + '\'' +
                ", value=" + value +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}