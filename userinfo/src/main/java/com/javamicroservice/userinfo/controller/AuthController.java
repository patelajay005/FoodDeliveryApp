package com.javamicroservice.userinfo.controller;

import com.javamicroservice.userinfo.dto.LoginRequest;
import com.javamicroservice.userinfo.dto.LoginResponse;
import com.javamicroservice.userinfo.dto.UserInformationDTO;
import com.javamicroservice.userinfo.service.UserInformationService;
import com.javamicroservice.userinfo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserInformationService userInformationService;

    @Autowired
    private JwtUtil jwtUtil;

    @Value("${jwt.expiration}")
    private Long expiration;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            // Authenticate user
            UserInformationDTO user = userInformationService.authenticateUser(
                loginRequest.getUserName(), 
                loginRequest.getPassword()
            );

            if (user != null) {
                // Generate JWT token
                String token = jwtUtil.generateToken(user.getUserName(), user.getUserid());

                // Create login response
                LoginResponse loginResponse = new LoginResponse();
                loginResponse.setToken(token);
                loginResponse.setUserId(user.getUserid());
                loginResponse.setUserName(user.getUserName());
                loginResponse.setExpiresIn(expiration);

                return ResponseEntity.ok(loginResponse);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid username or password");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error during authentication: " + e.getMessage());
        }
    }
}

