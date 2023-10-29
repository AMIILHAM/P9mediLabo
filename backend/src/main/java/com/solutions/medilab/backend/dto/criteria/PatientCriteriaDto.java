package com.solutions.medilab.backend.dto.criteria;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientCriteriaDto {
    private String nom;
    private String prenom;
    private String address;
    private String telephone;
}
