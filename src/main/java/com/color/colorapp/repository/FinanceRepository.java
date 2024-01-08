package com.color.colorapp.repository;

import com.color.colorapp.entity.Finance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinanceRepository extends JpaRepository<Finance, Long> {
    // Additional query methods if needed
}
