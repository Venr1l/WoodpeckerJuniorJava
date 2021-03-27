package com.danielsojka.woodpeckerjuniorjava.component;

import com.danielsojka.woodpeckerjuniorjava.service.PersonCSVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Order(0)
@Transactional
public class PersonApplicationListener implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    PersonCSVService personCSVService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        personCSVService.initPeopleData();
    }
}