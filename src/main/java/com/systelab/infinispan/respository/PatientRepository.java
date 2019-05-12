package com.systelab.infinispan.respository;

import com.systelab.infinispan.model.Patient;

public class PatientRepository {

    public Patient getPatient(String patientId) {

        delay(1000);
        Patient patient = new Patient();
        patient.setId(patientId);
        patient.setName("Peter");
        patient.setSurname("Fowler");
        return patient;
    }

    public Patient create(Patient patient) {
        return patient;
    }

    public Patient update(String id, Patient patient) {
        return patient;
    }

    public boolean delete(String id) {
        return true;
    }

    public void delay(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            // ...
            e.printStackTrace();
        }
    }
}