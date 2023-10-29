package com.solutions.medilab.backend.dto.token;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import com.solutions.medilab.backend.utils.Constant;


@Getter
@Setter
public class JwtResponseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("Statut")
    private Boolean statut;

    @JsonProperty("Message")
    private String message;

    @JsonProperty("Token")
    private transient Map<String, Object> token;

    public JwtResponseDto() {
        this.statut = true;
        this.message = Constant.AUTHENTICATE_SUCCESS;
        this.token = new LinkedHashMap<>();
    }

}

