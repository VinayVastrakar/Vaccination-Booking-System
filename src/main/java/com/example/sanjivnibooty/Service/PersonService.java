package com.example.sanjivnibooty.Service;

import com.example.sanjivnibooty.Exception.PersonNotFoundException;
import com.example.sanjivnibooty.Model.Person;
import com.example.sanjivnibooty.Repository.PersonRepository;
import com.example.sanjivnibooty.dto.RequestDto.AddPersonRequestDto;
import com.example.sanjivnibooty.dto.ResponseDto.AddPersonResponseDto;
import org.hibernate.annotations.SecondaryRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;
    public AddPersonRequestDto addPerson(AddPersonRequestDto addPersonRequestDto) {

        //Convert Request Dto -> Entity
        Person person = new Person();
        person.setName(addPersonRequestDto.getName());
        person.setAge(addPersonRequestDto.getAge());
        person.setEmailId(addPersonRequestDto.getEmailId());
        person.setGender(addPersonRequestDto.getGender());


        Person savedPerson = personRepository.save(person);

        //saves entity -> response dto
        AddPersonResponseDto addPersonResponseDto = new AddPersonResponseDto();

        addPersonResponseDto.setName(savedPerson.getName());
        addPersonResponseDto.setMessage("Congrats! you have been registred");

        return addPersonRequestDto;


    }

    public String updateEmail(String oldEmail, String newEmail) {
        Person person = personRepository.findByEmailId(oldEmail);
        if(person== null){
            throw new PersonNotFoundException("Sorry email dosen't exist");
        }
        person.setEmailId(newEmail);
        personRepository.save(person);
        return "Congrats! Your emaiil has been update successfully";
    }
}
