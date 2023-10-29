package com.solutions.medilab.backend.dto;

import com.solutions.medilab.backend.enums.Genre;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;


import java.time.LocalDate;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class PatientDto {

    private Long id;

    @NonNull
    private String nom;

    @NonNull
    private String prenom;

    @NonNull
    @DateTimeFormat(pattern = "YYYY-MM-DD")
    private LocalDate dateNaissance;

    @NonNull
    private Genre genre;

    private String adressePostale;

    private String numTelephone;

}
