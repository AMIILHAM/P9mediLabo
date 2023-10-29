package com.solutions.medilab.backend.model;

import lombok.*;

import javax.persistence.*;

@Entity(name = "USER")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String fullName;
    @NonNull
    @Column(unique = true)
    private String username;
    @NonNull
    private String password;
}
