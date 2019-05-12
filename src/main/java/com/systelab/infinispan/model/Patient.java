package com.systelab.infinispan.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient implements Serializable {

    private String id;
    private String surname;
    private String name;
    private String medicalNumber;
    private String email;
    private LocalDate dob;
    private Address address;


    public String getFullName() {
        return this.getSurname()+", "+this.getName();
    }
}