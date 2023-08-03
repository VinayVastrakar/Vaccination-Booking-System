package com.example.sanjivnibooty.controller;

import com.example.sanjivnibooty.Enum.DoseType;
import com.example.sanjivnibooty.Model.Dose;
import com.example.sanjivnibooty.Service.DoseService;
import com.example.sanjivnibooty.dto.RequestDto.BookDose1RequestDto;
import com.example.sanjivnibooty.dto.RequestDto.BookDose2RequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/dose")
public class DoseController {

    @Autowired
    DoseService doseService;

//    @PostMapping("/get-dose-1")
//    public ResponseEntity getDose1(@RequestParam("id") int personId , @RequestParam ("doseType")DoseType doseType){
//
//        try {
//            Dose doseTake = doseService.getDose1(personId, doseType);
//            return new ResponseEntity(doseTake, HttpStatus.CREATED);
//        }
//        catch (Exception e){
//            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//    }

    //get dose 1
    @PostMapping("/get-dose-1")
    public ResponseEntity getDose1(@RequestBody BookDose1RequestDto bookDose1RequestDto){

        try {
            Dose doseTake= doseService.getDose1(bookDose1RequestDto);
            return new ResponseEntity(doseTake,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // get Dose 2
    @PostMapping("/get-dose-2")
    public ResponseEntity getDose2(@RequestBody BookDose2RequestDto bookDose2RequestDto ){

        try{
            Dose doseTake = doseService.getDose2(bookDose2RequestDto);
            return new ResponseEntity(doseTake, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
