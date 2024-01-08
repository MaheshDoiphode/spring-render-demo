package com.color.colorapp.repository;

import com.color.colorapp.entity.UpiDetail;
import com.color.colorapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UpiDetailRepository extends JpaRepository<UpiDetail, Long> {
    UpiDetail findByUserIdAndUpiId(Long userId, String upiId);
}
