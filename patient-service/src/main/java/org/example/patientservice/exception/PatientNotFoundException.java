package org.example.patientservice.exception;

import lombok.Getter;

import java.util.UUID;

@Getter
public class PatientNotFoundException extends RuntimeException {
    private final UUID patientId;

    public PatientNotFoundException(String message, UUID patientId) {
        super(message);
        this.patientId = patientId;
    }
}
