package com.color.colorapp.repository;

import com.color.colorapp.entity.Finance;
import com.color.colorapp.entity.Round;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoundRepository extends MongoRepository<Round, String> {
    Round findTopByRoundIdStartingWithOrderByRoundIdDesc(String roundIdPrefix);

    Round findByRoundId(String roundId);
}

