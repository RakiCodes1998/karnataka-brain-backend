
package com.karnatakabrain.backend.repository;

import com.karnatakabrain.backend.entity.GameScore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameScoreRepository extends JpaRepository<GameScore, Long> {

    List<GameScore> findByUserIdOrderByPlayedAtDesc(Long userId);
}
