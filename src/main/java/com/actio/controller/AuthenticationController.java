package com.actio.controller;

import com.actio.dto.request.AuthenticationRequestDTO;
import com.actio.entity.User;
import com.actio.jwt.JwtTokenManager;
import com.actio.service.UserService;
import com.actio.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = Constants.API_ROOT + "/authenticate")
public class AuthenticationController {

    @Autowired
    private JwtTokenManager jwtTokenManager;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @PostMapping()
    public String generateToken(@RequestBody AuthenticationRequestDTO authenticationRequestDTO) throws Exception {
        User user;
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequestDTO.email(), authenticationRequestDTO.password())
            );

            user = userService.getUserByEmail(authenticationRequestDTO.email());

        } catch (Exception e) {
            throw new Exception("Invalid email or password");
        }

        return jwtTokenManager.generateToken(authenticationRequestDTO.email(), user.getId().toString(), user.getRole().toString());
    }
}
