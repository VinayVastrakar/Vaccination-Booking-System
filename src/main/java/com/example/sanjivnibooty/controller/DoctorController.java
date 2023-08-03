package com.example.sanjivnibooty.controller;

import com.example.sanjivnibooty.Service.DoctorService;
import com.example.sanjivnibooty.dto.RequestDto.DoctorRequestDto;
import com.example.sanjivnibooty.dto.ResponseDto.DoctorResponseDto;
import lombok.Getter;
import org.aspectj.weaver.ResolvedPointcutDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @PostMapping("/add")
    public ResponseEntity addDoctor(@RequestBody DoctorRequestDto doctorRequestDto){

        try{
            DoctorResponseDto doctorResponseDto= doctorService.addDoctor(doctorRequestDto);
            return new ResponseEntity(doctorResponseDto, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage() , HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-by-age-greater-than")
    public List<String> getByAgeGreaterThan(@RequestParam("age") int age){
        List<String> doctors = doctorService.getByAgeGreaterThan(age);
        return doctors;
    }

    //get the doctor with highest number of appoinments
    //get the list of doctors who belong to a perticular center;
    // api ro update email and/or age of a doctor

}
