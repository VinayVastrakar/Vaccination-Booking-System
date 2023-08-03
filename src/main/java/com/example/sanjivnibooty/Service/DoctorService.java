package com.example.sanjivnibooty.Service;

import com.example.sanjivnibooty.Exception.CenterNotFoundException;
import com.example.sanjivnibooty.Model.Doctor;
import com.example.sanjivnibooty.Model.VaccinationCenter;
import com.example.sanjivnibooty.Repository.DoctorRepository;
import com.example.sanjivnibooty.Repository.VaccinationCenterRepository;
import com.example.sanjivnibooty.dto.RequestDto.DoctorRequestDto;
import com.example.sanjivnibooty.dto.ResponseDto.CenterRersonseDto;
import com.example.sanjivnibooty.dto.ResponseDto.DoctorResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    VaccinationCenterRepository centerRepository;

    @Autowired
    DoctorRepository doctorRepository;

    public DoctorResponseDto addDoctor(DoctorRequestDto doctorRequestDto) {

        Optional<VaccinationCenter> vaccinationCenterOptional = centerRepository.findById(doctorRequestDto.getCenterId());
        if(vaccinationCenterOptional.isEmpty()){
            throw new CenterNotFoundException("Sorry! Worng Center Id");
        }
        VaccinationCenter center =vaccinationCenterOptional.get();

        Doctor doctor=new Doctor();
        doctor.setName(doctorRequestDto.getName());
        doctor.setAge(doctorRequestDto.getAge());
        doctor.setEmailId(doctorRequestDto.getEmailId());
        doctor.setGender(doctorRequestDto.getGender());
        doctor.setCenter(center);

        center.getDoctors().add(doctor);

        VaccinationCenter savedCenter = centerRepository.save(center);

        //perpare reseponse dto
        List<Doctor> doctors = savedCenter.getDoctors();
        Doctor latestSavedDoctor = doctors.get(doctors.size()-1);

        CenterRersonseDto centerRersonseDto= new CenterRersonseDto();
        centerRersonseDto.setCenterType(savedCenter.getCenterType());
        centerRersonseDto.setAddress(savedCenter.getAddress());
        centerRersonseDto.setCenterName(savedCenter.getCenterName());

        DoctorResponseDto doctorResponseDto=new DoctorResponseDto();
        doctorResponseDto.setName(latestSavedDoctor.getName());
        doctorResponseDto.setMessage("Congrats!! You have been registered!");
        doctorResponseDto.setCenterRersonseDto(centerRersonseDto);

        return doctorResponseDto;

    }

    public List<String> getByAgeGreaterThan(int age) {

         List<Doctor> doctors =doctorRepository.getByAgeGreaterThan(age);
         List<String > ans = new ArrayList<>();

        for (Doctor d: doctors) {
            ans.add(d.getName());
        }
        return ans;
    }
}
