package com.example.sanjivnibooty.Service;

import com.example.sanjivnibooty.Model.VaccinationCenter;
import com.example.sanjivnibooty.Repository.VaccinationCenterRepository;
import com.example.sanjivnibooty.dto.RequestDto.CenterRequestDto;
import com.example.sanjivnibooty.dto.ResponseDto.CenterRersonseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VaccinationCenterService {

    @Autowired
    VaccinationCenterRepository centerRepository;

    public CenterRersonseDto addCenter(CenterRequestDto centerRequestDto) {
        // request dto -> entity
        VaccinationCenter center = new VaccinationCenter();
        center.setCenterName(centerRequestDto.getCenterName());
        center.setCenterType(centerRequestDto.getCenterType());
        center.setAddress(centerRequestDto.getAddress());

        // save entity to db
        VaccinationCenter savedCenter = centerRepository.save(center);

        // entity -> response Dto
        CenterRersonseDto centerResponseDto = new CenterRersonseDto();
        centerResponseDto.setCenterName(savedCenter.getCenterName());
        centerResponseDto.setAddress(savedCenter.getAddress());
        centerResponseDto.setCenterType(savedCenter.getCenterType());
        return centerResponseDto;
    }
}
