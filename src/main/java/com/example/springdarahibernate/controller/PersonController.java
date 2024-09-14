package com.example.springdarahibernate.controller;


import com.example.springdarahibernate.entity.Person;
import com.example.springdarahibernate.service.PersonService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
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

    // Открытый доступ без авторизации
    @GetMapping("/public/info")
    public String getPublicInfo() {
        return "This is a public information endpoint!";
    }

    // Метод доступен только для пользователей с ролью "READ"
    @Secured("ROLE_READ")
    @GetMapping("/persons/by-city")
    public List<Person> getPersonsByCity(@RequestParam String city) {
        return personService.getPersonsByCity(city);
    }

    // Метод доступен только для пользователей с ролью "WRITE"
    @RolesAllowed("ROLE_WRITE")
    @GetMapping("/persons/add")
    public String addPerson() {
        return "Person added!";
    }

    // Метод доступен для пользователей с ролью "WRITE" или "DELETE"
    @PreAuthorize("hasRole('ROLE_WRITE') or hasRole('ROLE_DELETE')")
    @GetMapping("/persons/manage")
    public String managePerson() {
        return "Managing persons!";
    }

    // Метод проверяет username пользователя из Authentication
    @PreAuthorize("#username == authentication.principal.username")
    @GetMapping("/persons/personal-info")
    public String getPersonalInfo(@RequestParam String username) {
        return "Personal info for " + username;
    }
}
