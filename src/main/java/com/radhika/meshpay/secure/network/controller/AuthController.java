package com.radhika.meshpay.secure.network.controller;

import com.radhika.meshpay.secure.network.dto.AuthResponse;
import com.radhika.meshpay.secure.network.dto.LoginRequest;
import com.radhika.meshpay.secure.network.dto.RegisterRequest;
import com.radhika.meshpay.secure.network.security.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @Valid @RequestBody RegisterRequest registerRequest
    ) {

        AuthResponse response = authService.register(registerRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @Valid @RequestBody LoginRequest loginRequest
    ) {

        AuthResponse response = authService.login(loginRequest);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}