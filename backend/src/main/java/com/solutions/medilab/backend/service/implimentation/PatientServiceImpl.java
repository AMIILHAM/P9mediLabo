package com.solutions.medilab.backend.service.implimentation;

import com.solutions.medilab.backend.dto.PatientDto;
import com.solutions.medilab.backend.dto.criteria.PatientCriteriaDto;
import com.solutions.medilab.backend.mapper.PatientMapper;
import com.solutions.medilab.backend.model.Patient;
import com.solutions.medilab.backend.model.Patient_;
import com.solutions.medilab.backend.repository.PatientRepository;
import com.solutions.medilab.backend.service.PatientService;
import com.solutions.medilab.backend.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    @Override
    public Optional<Patient> getPatientById(Long id) {
        return this.patientRepository.findById(id);
    }

    @Override
    public Patient save(PatientDto patientDto) {
        Patient patient = this.patientMapper.patientDtoToPatient(patientDto);
        return this.patientRepository.save(patient);
    }

    @Override
    public void deleteById(Long id) {
         this.patientRepository.deleteById(id);
    }

    @Override
    public Page<Patient> getAllByCriteria(PatientCriteriaDto patientCriteriaDto, Pageable pageable) {
        return this.patientRepository.findAll(patientSpecification(patientCriteriaDto),pageable);
    }

    private Specification<Patient> patientSpecification(PatientCriteriaDto patientCriteriaDto) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            if (Utils.isNotBlank(patientCriteriaDto.getNom())) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get(Patient_.NOM), "%" + patientCriteriaDto.getNom() + "%"));
            }
            if (Utils.isNotBlank(patientCriteriaDto.getPrenom())) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get(Patient_.PRENOM), "%" + patientCriteriaDto.getPrenom() + "%"));
            }
            if (Utils.isNotBlank(patientCriteriaDto.getAddress())) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get(Patient_.ADRESSE_POSTALE), "%" + patientCriteriaDto.getAddress() + "%"));
            }
            if (Utils.isNotBlank(patientCriteriaDto.getTelephone())) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get(Patient_.NUM_TELEPHONE), "%" + patientCriteriaDto.getTelephone() + "%"));
            }

            return predicate;
        };
    }
}
