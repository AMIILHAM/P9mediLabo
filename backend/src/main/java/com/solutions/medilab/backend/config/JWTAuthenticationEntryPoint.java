package com.solutions.medilab.backend.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solutions.medilab.backend.exception.ResponseError;
import com.solutions.medilab.backend.utils.Constant;
import com.solutions.medilab.backend.utils.Utils;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

@Component
public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint, AccessDeniedHandler, Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        if (null!=authException) {
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            String code =response.getHeader(Constant.ERROR_CODE);
            if (Utils.isBlank(code)) {
                code = Constant.UN_AUTHORIZED;
            }
            String message =response.getHeader(Constant.ERROR_MESSAGE);
            if (Utils.isBlank(message)) {
                if (authException.getCause() != null) {
                    message = authException.getCause().getMessage();
                } else {
                    message = authException.getMessage();
                }
            }
            response.getOutputStream().write(new ObjectMapper().writeValueAsBytes(new ResponseError(code,message)));
        }
    }

    @Override
    public void handle(final HttpServletRequest request, final HttpServletResponse response, final AccessDeniedException exception) throws IOException {
        if (null!=exception) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getOutputStream().write(new ObjectMapper().writeValueAsBytes(new ResponseError(Constant.ACCESS_DENIED_ERROR, Constant.ACCESS_DENIED_ERROR)));
        }
    }
}