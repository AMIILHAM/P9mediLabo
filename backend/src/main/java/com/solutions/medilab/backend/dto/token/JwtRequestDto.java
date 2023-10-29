package com.solutions.medilab.backend.dto.token;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtRequestDto {
    @NotNull
    private String username;
    @NotNull
    private String password;
}
