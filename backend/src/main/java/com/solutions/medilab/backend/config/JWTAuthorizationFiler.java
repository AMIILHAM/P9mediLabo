package com.solutions.medilab.backend.config;

import com.solutions.medilab.backend.utils.Utils;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@AllArgsConstructor
public class JWTAuthorizationFiler extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        String jwt = jwtTokenProvider.resolveToken(httpServletRequest);
        if (Utils.isNotBlank(jwt) && jwtTokenProvider.validateToken(jwt,httpServletResponse)) {
            SecurityContextHolder.getContext().setAuthentication(jwtTokenProvider.getAuthentication(jwt));
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
