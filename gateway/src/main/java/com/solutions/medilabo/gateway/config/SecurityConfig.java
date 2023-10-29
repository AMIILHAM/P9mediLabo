package com.solutions.medilabo.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {


    @Bean
    public SecurityWebFilterChain filterChain(ServerHttpSecurity http) {
        return http
                .cors().disable()
                .csrf().disable()
                .authorizeExchange()
                .pathMatchers("/auth").permitAll()
                .anyExchange().authenticated()
                .and() //.authenticationManager(reactiveAuthenticationManager())
                .httpBasic().and()
                .build();
    }

//    @Bean
//    ReactiveAuthenticationManager reactiveAuthenticationManager(){
//        return new UserDetailsRepositoryReactiveAuthenticationManager(getInMemoryUserDetails());
//    }
//
//    @Bean
//    public MapReactiveUserDetailsService getInMemoryUserDetails() {
//        UserDetails userDetails = User.withDefaultPasswordEncoder().username("user").password("user")
//                .roles("USER")
//                .build();
//        return new MapReactiveUserDetailsService(userDetails);
//    }

}
