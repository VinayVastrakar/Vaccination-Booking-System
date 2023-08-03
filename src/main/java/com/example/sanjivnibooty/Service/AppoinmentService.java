package com.example.sanjivnibooty.Service;

import com.example.sanjivnibooty.Exception.DoctorNotFoundException;
import com.example.sanjivnibooty.Exception.PersonNotFoundException;
import com.example.sanjivnibooty.Model.Appointment;
import com.example.sanjivnibooty.Model.Doctor;
import com.example.sanjivnibooty.Model.Person;
import com.example.sanjivnibooty.Model.VaccinationCenter;
import com.example.sanjivnibooty.Repository.AppoinmentRepository;
import com.example.sanjivnibooty.Repository.DoctorRepository;
import com.example.sanjivnibooty.Repository.PersonRepository;
import com.example.sanjivnibooty.dto.RequestDto.BookAppointmentRequestDto;
import com.example.sanjivnibooty.dto.ResponseDto.BookAppointmentResponseDto;
import com.example.sanjivnibooty.dto.ResponseDto.CenterRersonseDto;
import org.hibernate.annotations.NaturalId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.Optional;
import java.util.UUID;

@Service
public class AppoinmentService {
    @Autowired
    PersonRepository personRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    AppoinmentRepository appoinmentRepository;

    @Autowired
    JavaMailSender javaMailSender;

    public BookAppointmentResponseDto bookAppoinment(BookAppointmentRequestDto bookAppointmentRequestDto) {
        Optional<Person> optionalPerson =personRepository.findById(bookAppointmentRequestDto.getPersonId());
        if(optionalPerson.isEmpty()){
            throw new PersonNotFoundException("Invalid personId");
        }
        Optional<Doctor> optionalDoctor = doctorRepository.findById(bookAppointmentRequestDto.getDoctorId());
        if(optionalDoctor.isEmpty()){
            throw new DoctorNotFoundException("Invalid DoctorId");
        }
        Person person= optionalPerson.get();
        Doctor doctor = optionalDoctor.get();

        //create appoinment object
        Appointment appointment= new Appointment();
        appointment.setAppointmentId(String.valueOf(UUID.randomUUID()));
        appointment.setPerson(person);
        appointment.setDoctor(doctor);

        Appointment savedAppoinment= appoinmentRepository.save(appointment);
        doctor.getAppointments().add(savedAppoinment);
        person.getAppointments().add(savedAppoinment);

        Doctor savedDoctor= doctorRepository.save(doctor);
        Person savedPerson= personRepository.save(person);
        VaccinationCenter center= savedDoctor.getCenter();

        //send an email
        String text ="Congrats!! "+savedPerson.getName()+ " Your appoinmenthas been booked with Doctor "+
                savedDoctor.getName()+". Your Vaccination Center name is "+ center.getCenterName()+
                " Please reach at this address "+ center.getAddress()+" at this time: "+savedAppoinment.getAppointmentDate()+
                "Dhanyawad!!!";

        SimpleMailMessage simpleMailMessage= new SimpleMailMessage();
        simpleMailMessage.setFrom("acciojobspring@gmail.com");
        simpleMailMessage.setTo(savedPerson.getEmailId());
        simpleMailMessage.setSubject("Congrats! Appoinment Done!!");
        simpleMailMessage.setText(text);

        javaMailSender.send(simpleMailMessage);

        //prepare for response dto
        CenterRersonseDto centerRersonseDto =new CenterRersonseDto();
        centerRersonseDto.setCenterName(center.getCenterName());
        centerRersonseDto.setAddress(center.getAddress());
        centerRersonseDto.setCenterType(center.getCenterType());

        BookAppointmentResponseDto bookAppointmentResponseDto=new BookAppointmentResponseDto();
        bookAppointmentResponseDto.setPersonName(savedPerson.getName());
        bookAppointmentResponseDto.setDoctorName(savedDoctor.getName());
        bookAppointmentResponseDto.setAppointmentId(savedAppoinment.getAppointmentId());
        bookAppointmentResponseDto.setAppointmentDate(savedAppoinment.getAppointmentDate());
        bookAppointmentResponseDto.setCenterRersonseDto(centerRersonseDto);

        return bookAppointmentResponseDto;


    }
}
