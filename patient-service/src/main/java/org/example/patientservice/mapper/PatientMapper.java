package org.example.patientservice.mapper;

import org.example.patientservice.dto.PatientRequestDTO;
import org.example.patientservice.dto.PatientResponseDTO;
import org.example.patientservice.model.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PatientMapper {
    @Mapping(target = "dateOfBirth", source = "dateOfBirth", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "registeredDate", source = "registeredDate", dateFormat = "yyyy-MM-dd")
    Patient toEntity(PatientRequestDTO patientDTO);

    @Mapping(target = "dateOfBirth", source = "dateOfBirth", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "id", source = "id")
    PatientResponseDTO toDTO(Patient patient);

    List<PatientResponseDTO> toListDTO(List<Patient> patients);
}

