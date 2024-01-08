package com.color.colorapp.service;

import com.color.colorapp.dto.BankDetailsDTO;
import com.color.colorapp.dto.UpiDTO;
import com.color.colorapp.dto.UserLoginDTO;
import com.color.colorapp.dto.UserRegistrationDTO;
import com.color.colorapp.entity.BankDetail;
import com.color.colorapp.entity.UpiDetail;
import com.color.colorapp.entity.User;
import com.color.colorapp.repository.BankDetailRepository;
import com.color.colorapp.repository.UpiDetailRepository;
import com.color.colorapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UpiDetailRepository upiDetailRepository;
    private final BankDetailRepository bankDetailRepository;
    @Autowired
    private UserService(UserRepository userRepository, UpiDetailRepository upiDetailRepository, BankDetailRepository bankDetailRepository){
        this.userRepository = userRepository;

        this.upiDetailRepository = upiDetailRepository;
        this.bankDetailRepository = bankDetailRepository;
    }


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

    // release 3:
    public void addUpiId(UpiDTO upiDto) {
        User user = userRepository.findByUsername(upiDto.getUsername());
        validateUserCredentials(user, upiDto.getPassword());

        UpiDetail existingUpiDetail = upiDetailRepository.findByUserIdAndUpiId(user.getUserId(), upiDto.getUpiId());
        if (existingUpiDetail == null) {
            UpiDetail upiDetail = new UpiDetail();
            upiDetail.setUserId(user.getUserId());
            upiDetail.setUpiId(upiDto.getUpiId());
            upiDetailRepository.save(upiDetail);
        } else {
            throw new IllegalArgumentException("UPI ID already exists for this user");
        }
    }

    public void addBankDetails(BankDetailsDTO bankDetailsDto) {
        User user = userRepository.findByUsername(bankDetailsDto.getUsername());
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        BankDetail existingBankDetail = bankDetailRepository.findByUserIdAndBankNameAndAccountNumber(
                user.getUserId(), bankDetailsDto.getBankName(), bankDetailsDto.getAccountNumber());
        if (existingBankDetail == null) {
            BankDetail bankDetail = new BankDetail();
            bankDetail.setUserId(user.getUserId());
            bankDetail.setBankName(bankDetailsDto.getBankName());
            bankDetail.setAccountNumber(bankDetailsDto.getAccountNumber());
            bankDetail.setIfscCode(bankDetailsDto.getIfscCode());
            bankDetailRepository.save(bankDetail);
        } else {
            throw new IllegalArgumentException("Bank account already exists for this user");
        }
    }

    private void validateUserCredentials(User user, String password) {
        if (user == null || !user.getTextPassword().equals(password)) {
            throw new IllegalArgumentException("Invalid username or password");
        }
    }

}
