package com.solutions.medilab.backend.service;

import com.solutions.medilab.backend.dto.PatientDto;
import com.solutions.medilab.backend.dto.criteria.PatientCriteriaDto;
import com.solutions.medilab.backend.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PatientService {
    Optional<Patient> getPatientById(Long id);
    Patient save(PatientDto patient);
    void deleteById(Long id);
    Page<Patient> getAllByCriteria(PatientCriteriaDto patientCriteriaDto,Pageable pageable);
}
