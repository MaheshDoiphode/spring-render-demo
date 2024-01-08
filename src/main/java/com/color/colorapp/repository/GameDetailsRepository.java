package com.color.colorapp.repository;

import com.color.colorapp.entity.Finance;
import com.color.colorapp.entity.GameDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameDetailsRepository extends JpaRepository<GameDetails, Integer> {

    GameDetails findTopByOrderByIdDesc();
}
