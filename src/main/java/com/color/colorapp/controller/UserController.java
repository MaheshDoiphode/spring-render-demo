package com.color.colorapp.controller;

import com.color.colorapp.dto.BankDetailsDTO;
import com.color.colorapp.dto.UpiDTO;
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
    public ResponseEntity<String> registerUser(@RequestBody Object userDto) {
        // Placeholder for registration logic
        return ResponseEntity.ok("Registration Okay");
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody Object loginDto) {
        // Placeholder for login logic
        return ResponseEntity.ok("Login Okay");
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
