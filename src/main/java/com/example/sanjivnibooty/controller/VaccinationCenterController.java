package com.example.sanjivnibooty.controller;

import com.example.sanjivnibooty.Service.VaccinationCenterService;
import com.example.sanjivnibooty.dto.RequestDto.CenterRequestDto;
import com.example.sanjivnibooty.dto.ResponseDto.CenterRersonseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/center")
public class VaccinationCenterController {

    @Autowired
    VaccinationCenterService centerService;

    @PostMapping("/add")
    public CenterRersonseDto addCenter(@RequestBody CenterRequestDto centerRequestDto){

        CenterRersonseDto centerResponseDto = centerService.addCenter(centerRequestDto);
        return centerResponseDto;
    }

    // get all the doctors at centers of a particular centerType

    // get the center with highest number of doctors

    // get the center with highest number of doctors among a particular centerType

}
