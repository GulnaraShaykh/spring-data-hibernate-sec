package com.example.springdarahibernate.controller;


import com.example.springdarahibernate.entity.Person;
import com.example.springdarahibernate.service.PersonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/persons/by-city")
    public List<Person> getPersonsByCity(@RequestParam String city) {
        return personService.getPersonsByCity(city);
    }
}
