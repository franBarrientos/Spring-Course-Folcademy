package com.saludo.api.controllers;

import com.saludo.api.domain.dtos.ResponseWithMessage;
import com.saludo.api.domain.dtos.AuthCredentialsDTO;
import com.saludo.api.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping
    public ResponseEntity<ResponseWithMessage> login(@Valid @RequestBody AuthCredentialsDTO authCredentialsDTO) {
        return ResponseEntity.ok(
                ResponseWithMessage.builder()
                        .resource(this.authService.login(authCredentialsDTO))
                        .message("login was completed succesfully!")
                        .build());
    }
    @PostMapping("/register")
    public ResponseEntity<ResponseWithMessage> register(@Valid @RequestBody AuthCredentialsDTO authCredentialsDTO) {
        return ResponseEntity.ok(
                ResponseWithMessage.builder()
                        .resource(this.authService.register(authCredentialsDTO))
                        .message("register was completed succesfully!")
                        .build());
    }


}
