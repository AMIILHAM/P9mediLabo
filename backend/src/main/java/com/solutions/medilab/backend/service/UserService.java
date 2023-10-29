package com.solutions.medilab.backend.service;

import com.solutions.medilab.backend.model.AppUser;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public interface UserService {

    Optional<AppUser> loadUserByUsername(String username) throws UsernameNotFoundException;
}
