package com.example.sanjivnibooty.Repository;

import com.example.sanjivnibooty.Model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Integer> {
    List<Doctor> getByAgeGreaterThan(int age);
}
