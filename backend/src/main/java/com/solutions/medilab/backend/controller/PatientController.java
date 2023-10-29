package com.solutions.medilab.backend.controller;

import com.solutions.medilab.backend.dto.PatientDto;
import com.solutions.medilab.backend.dto.criteria.PatientCriteriaDto;
import com.solutions.medilab.backend.model.Patient;
import com.solutions.medilab.backend.service.PatientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController @Slf4j
@RequiredArgsConstructor
public class PatientController {


    private final PatientService patientService;

    @GetMapping("/patients")
    public ResponseEntity<Page<Patient>> getAllByCriteria(PatientCriteriaDto patientCriteriaDto, Pageable pageable) {
        log.info("Request for get all patients");
        return ResponseEntity.ok(patientService.getAllByCriteria(patientCriteriaDto,pageable));

    }

    @GetMapping("/patients/{id}")
    public Patient getPatient(@PathVariable Long id) {
        log.info("Request for get patient {}",id);
        return patientService.getPatientById(id).orElse(null);
    }

    @PostMapping("/patients")
    public Patient createOrUpdatePatient(@RequestBody PatientDto patientDto) {
        return patientService.save(patientDto);
    }

    @DeleteMapping("/patients/{id}")
    public void deletePatient(@PathVariable Long id) {
        log.info("Request for delete patient : {}", id);
        patientService.deleteById(id);
    }
}