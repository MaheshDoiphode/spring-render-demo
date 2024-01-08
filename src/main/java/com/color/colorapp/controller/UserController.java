package com.color.colorapp.controller;

import com.color.colorapp.dto.*;
import com.color.colorapp.entity.User;
import com.color.colorapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegistrationDTO userDto) {
        try {
            User newUser = userService.registerUser(userDto);
            return ResponseEntity.ok("User registered successfully with ID: " + newUser.getUserId());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Registration failed: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserLoginDTO loginDto) {
        try {
            boolean isLoginSuccessful = userService.loginUser(loginDto);
            if (isLoginSuccessful) {
                return ResponseEntity.ok("Login successful for user: " + loginDto.getUsername());
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed: Invalid username or password");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Login failed: " + e.getMessage());
        }
    }


    // release 3:
    @PostMapping("/add_upi_option")
    public ResponseEntity<?> addUpiOption(@RequestBody UpiDTO upiDto) {
        try {
            userService.addUpiId(upiDto);
            return ResponseEntity.ok("UPI ID added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/add_bank_option")
    public ResponseEntity<?> addBankOption(@RequestBody BankDetailsDTO bankDetailsDto) {
        try {
            userService.addBankDetails(bankDetailsDto);
            return ResponseEntity.ok("Bank details added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


}
