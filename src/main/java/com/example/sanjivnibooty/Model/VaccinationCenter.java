package com.example.sanjivnibooty.Model;

import com.example.sanjivnibooty.Enum.CenterType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.apache.catalina.LifecycleState;

import java.util.ArrayList;
import java.util.List;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VaccinationCenter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String centerName;
    @Enumerated(value = EnumType.STRING)
    CenterType centerType;

    String address;

    @OneToMany(mappedBy = "center", cascade = CascadeType.ALL)
    List<Doctor> doctors = new ArrayList<>();

}
