package org.example.patientservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.patientservice.dto.PatientRequestDTO;
import org.example.patientservice.dto.PatientResponseDTO;
import org.example.patientservice.exception.EmailAlreadyExistsException;
import org.example.patientservice.exception.PatientNotFoundException;
import org.example.patientservice.grpc.BillingServiceGrpcClient;
import org.example.patientservice.mapper.PatientMapper;
import org.example.patientservice.model.Patient;
import org.example.patientservice.repository.PatientRepository;
import org.example.patientservice.service.PatientService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {
    private final PatientMapper patientMapper;
    private final PatientRepository patientRepository;
    private final BillingServiceGrpcClient billingServiceGrpcClient;

    @Override
    public List<PatientResponseDTO> getPatients() {
        return patientMapper.toListDTO(patientRepository.findAll());
    }

    @Override
    public PatientResponseDTO createPatient(PatientRequestDTO patientDTO) {
        if (patientRepository.existsByEmail(patientDTO.getEmail())) {
            throw new EmailAlreadyExistsException("Patient with email=" + patientDTO.getEmail() + " already exists");
        }

        Patient patient = patientRepository.save(patientMapper.toEntity(patientDTO));

        billingServiceGrpcClient.createBillingAccount(patient.getId().toString(), patient.getName(), patient.getEmail());
        return patientMapper.toDTO(patient);
    }

    @Override
    public PatientResponseDTO updatePatient(UUID id, PatientRequestDTO patientDTO) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found with id=" + id, id));

        if (patientRepository.existsByEmailAndIdNot(patientDTO.getEmail(), id)) {
            throw new EmailAlreadyExistsException("Patient with email=" + patientDTO.getEmail() + " already exists");
        }

        patient.setName(patientDTO.getName());
        patient.setEmail(patientDTO.getEmail());
        patient.setAddress(patientDTO.getAddress());
        patient.setDateOfBirth(LocalDate.parse(patientDTO.getDateOfBirth()));

        return patientMapper.toDTO(patientRepository.save(patient));
    }

    @Override
    public void deletePatient(UUID id) {
        patientRepository.deleteById(id);
    }
}
