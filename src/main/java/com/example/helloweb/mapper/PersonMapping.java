package com.example.helloweb.mapper;

import com.example.helloweb.dto.PersonDto;
import com.example.helloweb.entity.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonMapping {
    public PersonDto toPersonDto(Person person){
        return  new PersonDto(person.getFirstName(),person.getLastName(), person.getAge());
    }

    public Person fromPersonDto(PersonDto person){
        return  new Person(person.getFirstName(),person.getLastName(), person.getAge());
    }
}
