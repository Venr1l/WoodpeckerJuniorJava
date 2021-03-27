package com.danielsojka.woodpeckerjuniorjava.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JokeValue {

    private Integer id;
    private String joke;
    private List<String> categories = null;
    private final Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return "JokeValue{" +
                "id=" + id +
                ", joke='" + joke + '\'' +
                ", categories=" + categories +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}