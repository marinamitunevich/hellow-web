package com.example.helloweb.controller;

import com.example.helloweb.dto.PersonDto;
import com.example.helloweb.entity.Person;
import com.example.helloweb.mapper.PersonMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.websocket.server.PathParam;
import java.util.Arrays;
import java.util.List;

@Controller
public class HelloWebController {

    private final PersonMapping personMapping;

    public HelloWebController(PersonMapping personMapping){
        this.personMapping = personMapping;

    }

    @GetMapping("/web")
    public String helloWeb(){

        return "h";
    }

    @GetMapping("/hello2/{userName}")
    public String helloWebNew(@PathVariable(name = "userName") String name, Model model){
        Person person = new Person("Max", "Mustermann", 30);
        Person person2 = new Person("Max", "Mustermann", 32);
        Person person3 = new Person("Max", "Mustermann", 33);
        List<PersonDto> personList = Arrays.asList(personMapping.toPersonDto(person),
                personMapping.toPersonDto(person2),
                personMapping.toPersonDto(person3),
                personMapping.toPersonDto(person));

        String str = "Name: "+ name.toUpperCase();
        model.addAttribute("userName", str);
        model.addAttribute("lists",personList);
        System.out.println(name);


        return "hello-web";
    }
}
