package com.example.helloweb.controller;

import com.example.helloweb.dto.PersonDto;
import com.example.helloweb.entity.Person;
import com.example.helloweb.mapper.PersonMapping;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/person")

public class PersonController {

    private final PersonMapping personMapper;

    private List<Person> persons = new ArrayList<>();

    public PersonController(PersonMapping personMapper) {
        this.personMapper = personMapper;
    }

    @ResponseBody
    @GetMapping("/person/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PersonDto getPersonById(@PathVariable(name = "id", required = true) int personId) {
        Person person = persons.stream()
                .filter(person1 -> person1.getId() == personId)
                .findFirst()
                .get();

        return personMapper.toPersonDto(person);
    }

    @ResponseBody
    @GetMapping("/persons")
    @ResponseStatus(HttpStatus.OK)
    public List<PersonDto> getPersons() {
        return persons
                .stream()
                .map(personMapper::toPersonDto)
                .collect(Collectors.toList());
    }

    @ResponseBody
    @PostMapping("/person")
    @ResponseStatus(HttpStatus.CREATED)
    public void addPerson(@RequestBody PersonDto personDto) {
        Person personToAdd = personMapper.fromPersonDto(personDto);

        int id;
        if (persons.size() > 0) {
            int lastId = persons.get(persons.size() - 1).getId();
            id = ++lastId;
        } else {
            id = 1;
        }

        personToAdd.setId(id);

        persons.add(personToAdd);
    }

    @ResponseBody
    @DeleteMapping("/delete-persons/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<PersonDto> deletePersons(@PathVariable(name = "id", required = true) int personId) {

        persons.removeIf(person -> person.getId() == personId);

        return persons.stream().map(personMapper::toPersonDto).collect(Collectors.toList());
    }

    @ResponseBody
    @PutMapping("/person/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<PersonDto> updatePersonById(@PathVariable(name = "id", required = true) int personId, @RequestBody PersonDto personDto) {
        Person person = persons.stream()
                .filter(person1 -> person1.getId() == personId)
                .findFirst()
                .get();

        person.setAge(personDto.getAge());
        person.setFirstName(personDto.getFirstName());
        person.setLastName(personDto.getLastName());

        return persons.stream().map(personMapper::toPersonDto).collect(Collectors.toList());

    }

}
