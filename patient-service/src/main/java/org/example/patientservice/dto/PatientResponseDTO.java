package org.example.patientservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Response object representing a patient")
public class PatientResponseDTO {
    @Schema(
            description = "Unique identifier of the patient",
            example = "a3c0bcb5-57f4-4a6f-bf63-7a6f7a2e8abc"
    )
    private String id;

    @Schema(
            description = "Full name of the patient",
            example = "John Doe"
    )
    private String name;

    @Schema(
            description = "Email address of the patient",
            example = "john.doe@example.com"
    )
    private String email;

    @Schema(
            description = "Home address of the patient",
            example = "123 Main St, New York, NY"
    )
    private String address;

    @Schema(
            description = "Date of birth in YYYY-MM-DD format",
            example = "1990-05-15"
    )
    private String dateOfBirth;
}