package com.color.colorapp.repository;

import com.color.colorapp.entity.BankDetail;
import com.color.colorapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankDetailRepository extends JpaRepository<BankDetail, Long> {
    BankDetail findByUserIdAndBankNameAndAccountNumber(Long userId, String bankName, String accountNumber);
}
