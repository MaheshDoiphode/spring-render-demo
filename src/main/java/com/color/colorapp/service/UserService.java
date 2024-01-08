package com.color.colorapp.service;

import com.color.colorapp.dto.UserLoginDTO;
import com.color.colorapp.dto.UserRegistrationDTO;
import com.color.colorapp.entity.User;
import com.color.colorapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public User registerUser(UserRegistrationDTO registrationDTO) {
        User newUser = new User();
        newUser.setUsername(registrationDTO.getUsername());
        newUser.setEmail(registrationDTO.getEmail());
        newUser.setTextPassword(registrationDTO.getPassword());
        newUser.setDateOfBirth(registrationDTO.getDateOfBirth().atStartOfDay());
        newUser.setContactNumber(registrationDTO.getContactNumber());
        newUser.setRegistrationDate(LocalDateTime.now());

        return userRepository.save(newUser);
    }

    public boolean loginUser(UserLoginDTO loginDTO) {
        User user = userRepository.findByUsername(loginDTO.getUsername());
        return user != null && loginDTO.getPassword().equals(user.getTextPassword());
    }
}
