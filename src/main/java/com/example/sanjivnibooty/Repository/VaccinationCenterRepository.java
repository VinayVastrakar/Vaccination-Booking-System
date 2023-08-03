package com.example.sanjivnibooty.Repository;

import com.example.sanjivnibooty.Model.VaccinationCenter;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccinationCenterRepository extends JpaRepository<VaccinationCenter, Integer> {
}
