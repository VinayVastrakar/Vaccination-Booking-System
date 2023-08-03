package com.example.sanjivnibooty.Repository;

import com.example.sanjivnibooty.Model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person , Integer> {
    Person findByEmailId(String oldEmail);

}
