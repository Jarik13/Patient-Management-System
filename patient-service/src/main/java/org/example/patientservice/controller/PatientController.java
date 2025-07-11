package org.example.patientservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.groups.Default;
import lombok.RequiredArgsConstructor;
import org.example.patientservice.dto.PatientRequestDTO;
import org.example.patientservice.dto.PatientResponseDTO;
import org.example.patientservice.dto.validators.CreatePatientValidationGroup;
import org.example.patientservice.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
@Tag(name = "Patients", description = "Endpoints for managing patient records")
public class PatientController {
    private final PatientService patientService;

    @Operation(
            summary = "Get all patients",
            description = "Retrieves a list of all registered patients"
    )
    @ApiResponse(responseCode = "200", description = "Patients retrieved successfully")
    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> getPatients() {
        return ResponseEntity.ok(patientService.getPatients());
    }

    @Operation(
            summary = "Create a new patient",
            description = "Registers a new patient with provided personal information"
    )
    @ApiResponse(responseCode = "200", description = "Patient created successfully")
    @PostMapping
    public ResponseEntity<PatientResponseDTO> createPatient(
            @Validated({Default.class, CreatePatientValidationGroup.class}) @RequestBody PatientRequestDTO patientDTO
    ) {
        return ResponseEntity.ok(patientService.createPatient(patientDTO));
    }

    @Operation(
            summary = "Update an existing patient",
            description = "Updates the information of an existing patient identified by UUID"
    )
    @ApiResponse(responseCode = "200", description = "Patient updated successfully")
    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> updatePatient(
            @PathVariable UUID id,
            @Validated({Default.class}) @RequestBody PatientRequestDTO patientDTO
    ) {
        return ResponseEntity.ok(patientService.updatePatient(id, patientDTO));
    }

    @Operation(
            summary = "Delete an existing patient",
            description = "Deletes the information of an existing patient identified by UUID"
    )
    @ApiResponse(responseCode = "204", description = "Patient deleted successfully")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable UUID id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
}