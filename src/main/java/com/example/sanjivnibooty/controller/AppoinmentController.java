package com.example.sanjivnibooty.controller;

import com.example.sanjivnibooty.Service.AppoinmentService;
import com.example.sanjivnibooty.dto.RequestDto.BookAppointmentRequestDto;
import com.example.sanjivnibooty.dto.ResponseDto.BookAppointmentResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appoinment")
public class AppoinmentController {

    @Autowired
    AppoinmentService appoinmentService;

    @PostMapping("/book")
    public ResponseEntity bookAppoinment(@RequestBody BookAppointmentRequestDto bookAppointmentRequestDto){
        try{
            BookAppointmentResponseDto bookAppointmentResponseDto =
                    appoinmentService.bookAppoinment(bookAppointmentRequestDto);
            return new ResponseEntity(bookAppointmentResponseDto, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    //get all the appoinments of a perticular doctor;

    // get all the appoinments for a particular person;
}
