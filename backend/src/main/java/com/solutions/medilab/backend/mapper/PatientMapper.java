package com.solutions.medilab.backend.mapper;

import com.solutions.medilab.backend.model.Patient;
import com.solutions.medilab.backend.dto.PatientDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface PatientMapper {

    Patient patientDtoToPatient(PatientDto patientDto);
}
