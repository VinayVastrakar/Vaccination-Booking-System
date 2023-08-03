package com.example.sanjivnibooty.Repository;

import com.example.sanjivnibooty.Model.Dose;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoseRespository extends JpaRepository<Dose, Integer> {


}
