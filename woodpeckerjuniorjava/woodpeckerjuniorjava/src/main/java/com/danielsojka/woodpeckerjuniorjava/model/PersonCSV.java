package com.danielsojka.woodpeckerjuniorjava.model;

import com.opencsv.bean.CsvBindByName;

public class PersonCSV {

    @CsvBindByName
    private String name;
    @CsvBindByName
    private Integer age;
    @CsvBindByName
    private String hobby;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    @Override
    public String toString() {
        return "PersonCSV{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", hobby='" + hobby + '\'' +
                '}';
    }
}