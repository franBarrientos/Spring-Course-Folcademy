package com.saludo.api.services;

import com.saludo.api.domain.dtos.AuthenticationResponse;
import com.saludo.api.domain.dtos.AuthCredentialsDTO;
import com.saludo.api.domain.entities.UserEntity;
import com.saludo.api.domain.repositories.UserRepository;
import com.saludo.api.exceptions.exceptionKinds.BadRequestException;
import com.saludo.api.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;


    public AuthenticationResponse register(AuthCredentialsDTO authCredentialsDTO) {

        if (userRepository.existsByUsername(authCredentialsDTO.getUsername())) {
            throw new BadRequestException("User already exists");
        }

        UserEntity user = UserEntity.builder()
                .username(authCredentialsDTO.getUsername())
                .password(this.passwordEncoder.encode(authCredentialsDTO.getPassword()))
                .build();
        userRepository.save(user);
        return new AuthenticationResponse(this.jwtService.generateToken(user));
    }

    public AuthenticationResponse login(AuthCredentialsDTO authCredentialsDTO) {
        authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(authCredentialsDTO.getUsername(),
                                authCredentialsDTO.getPassword()));
        UserDetails user = userDetailsService.loadUserByUsername(authCredentialsDTO.getUsername());

        return new AuthenticationResponse(this.jwtService.generateToken(user));
    }
}
