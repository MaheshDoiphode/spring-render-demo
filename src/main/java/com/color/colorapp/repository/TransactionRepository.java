package com.color.colorapp.repository;

import com.color.colorapp.entity.Round;
import com.color.colorapp.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TransactionRepository extends MongoRepository<Transaction, String> {
}

