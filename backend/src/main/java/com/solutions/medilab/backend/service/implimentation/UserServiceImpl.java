package com.solutions.medilab.backend.service.implimentation;

import com.solutions.medilab.backend.model.AppUser;
import com.solutions.medilab.backend.repository.UserRepository;
import com.solutions.medilab.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Override
    public Optional<AppUser> loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username);
    }
}
