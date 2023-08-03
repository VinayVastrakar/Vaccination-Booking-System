package com.example.sanjivnibooty.Repository;

import com.example.sanjivnibooty.Model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppoinmentRepository extends JpaRepository<Appointment, Integer> {
}
