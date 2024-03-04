package com.pula.service;

import com.pula.dto.AuthenticationUserDetailsDTO;
import com.pula.entity.User;
import com.pula.repository.UserRepository;
import com.pula.util.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthenticationUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()){
            return new AuthenticationUserDetailsDTO(user.get());
        } else {
            throw new UserNotFoundException(User.class.getSimpleName() + " with email: " + email);
        }
    }
}
