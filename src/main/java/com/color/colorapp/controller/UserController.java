package com.color.colorapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Object userDto) {
        // Placeholder for registration logic
        return ResponseEntity.ok("Registration Okay");
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody Object loginDto) {
        // Placeholder for login logic
        return ResponseEntity.ok("Login Okay");
    }
}
