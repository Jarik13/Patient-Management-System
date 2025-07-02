package org.example.patientservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.patientservice.dto.validators.CreatePatientValidationGroup;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request payload for creating or updating a patient")
public class PatientRequestDTO {
    @Schema(
            description = "Full name of the patient",
            example = "John Doe",
            maxLength = 100
    )
    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name cannot exceed 100 characters")
    private String name;

    @Schema(
            description = "Email address of the patient",
            example = "john.doe@example.com"
    )
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @Schema(
            description = "Home address of the patient",
            example = "123 Main St, New York, NY"
    )
    @NotBlank(message = "Address is required")
    private String address;

    @Schema(
            description = "Date of birth in YYYY-MM-DD format",
            example = "1990-05-15"
    )
    @NotBlank(message = "Date of birth is required")
    private String dateOfBirth;

    @Schema(
            description = "Date when the patient was registered",
            example = "2024-06-01"
    )
    @NotBlank(groups = CreatePatientValidationGroup.class, message = "Registered date is required")
    private String registeredDate;
}