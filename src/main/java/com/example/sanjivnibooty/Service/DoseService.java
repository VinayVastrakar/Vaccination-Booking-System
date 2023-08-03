package com.example.sanjivnibooty.Service;

import com.example.sanjivnibooty.Enum.DoseType;
import com.example.sanjivnibooty.Exception.DoseAlreadyTakenException;
import com.example.sanjivnibooty.Exception.PersonNotFoundException;
import com.example.sanjivnibooty.Model.Dose;
import com.example.sanjivnibooty.Model.Person;
import com.example.sanjivnibooty.Repository.DoseRespository;
import com.example.sanjivnibooty.Repository.PersonRepository;
import com.example.sanjivnibooty.dto.RequestDto.BookDose1RequestDto;
import com.example.sanjivnibooty.dto.RequestDto.BookDose2RequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DoseService {

    @Autowired
    DoseRespository doseRespository;

    @Autowired
    PersonRepository personRepository;

//    public Dose getDose1(int personId, DoseType doseType) {
//        Optional<Person> optionalPerson = personRepository.findById(personId);
//
//        //check if person exist or not
//        if(!optionalPerson.isPresent()){
//            throw new PersonNotFoundException("Invalid PersonId");
//        }
//
//        Person person = optionalPerson.get();
//        //check if dose 1 is already taken
//        if(person.isDose1Taken()){
//            throw new DoseAlreadyTakenException("Dose 1 already taken");
//        }
//
//        //Create a Dose
//        Dose dose= new Dose();
//        dose.setDoseId(String.valueOf(UUID.randomUUID()));
//        dose.setDoseType(doseType);
//        dose.setPerson(person);
//
//        person.setDose1Taken(true);
//        person.getDoseTaken().add(dose);
//        personRepository.save(person);
//
//        return doseRespository.save(dose);
//
//    }

    public Dose getDose1(BookDose1RequestDto bookDose1RequestDto){

        Optional<Person> optionalPerson = personRepository.findById(bookDose1RequestDto.getPersonId());

        //check if person exist or not
        if(!optionalPerson.isPresent()){
            throw new PersonNotFoundException("Invalid PersonId");
        }

        Person person = optionalPerson.get();

        //check if dose 1 is already exist
        if(person.isDose1Taken()){
            throw new DoseAlreadyTakenException("Dose 1 Already taken");
        }

        //create a dose RequestDto
        Dose dose= new Dose();
        dose.setDoseId(String.valueOf(UUID.randomUUID()));
        dose.setDoseType(bookDose1RequestDto.getDoseType());
        dose.setPerson(person);

        person.setDose1Taken(true);
        person.getDoseTaken().add(dose);
        Person savedPerson = personRepository.save(person);

        return savedPerson.getDoseTaken().get(0);

    }

    public Dose getDose2(BookDose2RequestDto bookDose2RequestDto) {
        Optional<Person> optionalPerson = personRepository.findById(bookDose2RequestDto.getPersonId());

        if(!optionalPerson.isPresent()){
            throw new PersonNotFoundException("Invalid PersonId");
        }
        Person person= optionalPerson.get();

        if(person.isDose2Taken()){
            throw new DoseAlreadyTakenException("Dose 2 Already taken");
        }

        Dose dose = new Dose();
        dose.setDoseId(String.valueOf(UUID.randomUUID()));
        dose.setDoseType(bookDose2RequestDto.getDoseType());
        dose.setPerson(person);

        person.setDose2Taken(true);
        person.getDoseTaken().add(dose);
        Person savedPerson = personRepository.save(person);

        return savedPerson.getDoseTaken().get(0);


    }
}
