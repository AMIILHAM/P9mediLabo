package com.solutions.medilab.backend.service.implimentation;

import com.solutions.medilab.backend.model.AppUser;
import com.solutions.medilab.backend.repository.UserRepository;
import com.solutions.medilab.backend.service.UserService;
import com.solutions.medilab.backend.utils.Constant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Check credentials for : " + username);
        AppUser appUser = userService.loadUserByUsername(username).orElse(null);

        if (appUser != null) {
            log.info("Credentials Valid : " + username);
            return new User(appUser.getUsername(),appUser.getPassword(), Collections.singleton(new SimpleGrantedAuthority("USER")));
        } else {
            throw new UsernameNotFoundException(Constant.USER_NOT_FOUND + " : " + username);
        }
    }
}
