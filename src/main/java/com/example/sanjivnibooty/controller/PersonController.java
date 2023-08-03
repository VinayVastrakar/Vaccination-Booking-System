package com.example.sanjivnibooty.controller;

import com.example.sanjivnibooty.Model.Person;
import com.example.sanjivnibooty.Service.PersonService;
import com.example.sanjivnibooty.dto.RequestDto.AddPersonRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonService personService;
    @PostMapping("/add")
    public ResponseEntity addPerson(@RequestBody AddPersonRequestDto addPersonRequestDto){
        try{
            AddPersonRequestDto personResponse = personService.addPerson(addPersonRequestDto);
            return new ResponseEntity(personResponse, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity("Email alerady exists" ,HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/update-email")
    public ResponseEntity updateEmail(@RequestParam ("oldEmail") String oldEmail,
                                      @RequestParam("newEmail") String newEmail){
        try{
            String response= personService.updateEmail(oldEmail,newEmail);
            return new ResponseEntity(response,HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
