package com.example.sanjivnibooty.Model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Certificate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String certificate;

    String confirmationMessage;

    @OneToOne
    @JoinColumn
    Person person;
}
