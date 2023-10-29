package com.solutions.medilab.backend.model;

import com.solutions.medilab.backend.enums.Genre;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "PATIENT")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private String prenom;

    @DateTimeFormat(pattern = "YYYY-MM-DD")
    private LocalDate dateNaissance;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    private String adressePostale;

    private String numTelephone;
}
