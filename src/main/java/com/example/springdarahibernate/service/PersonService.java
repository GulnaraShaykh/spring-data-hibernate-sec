package com.example.springdarahibernate.service;

import com.example.springdarahibernate.entity.Person;
import com.example.springdarahibernate.repository.PersonRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PersonService {

    @PersistenceContext
    private EntityManager entityManager;

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

   @Transactional
    public List<Person> getPersonsByCity(String city) {
        // Используем репозиторий для поиска по городу
        return personRepository.findByCityOfLiving(city);
    }
}
