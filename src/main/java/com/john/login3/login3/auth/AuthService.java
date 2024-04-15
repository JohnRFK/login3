package com.john.login3.login3.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.john.login3.login3.jwt.JwtService;
import com.john.login3.login3.models.Role;
import com.john.login3.login3.models.User;
import com.john.login3.login3.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest loginRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        UserDetails user = userRepository.findByUsername(loginRequest.getUsername()).orElseThrow();
        String token = jwtService.getToken(user);
        return AuthResponse.builder()
            .token(token)
            .build();
    }

    public AuthResponse register(RegisterRequest registerRequest){
        User user = User.builder()
                        .username(registerRequest.getUsername())
                        .password(passwordEncoder.encode(registerRequest.getPassword()))
                        .firstName(registerRequest.getFirstName())
                        .lastName(registerRequest.getLastName())
                        .country(registerRequest.getCountry())
                        .role(Role.USER)
                        .build();
        userRepository.save(user);
        return AuthResponse.builder()
                        .token(jwtService.getToken(user))
                        .build();
    }
}
