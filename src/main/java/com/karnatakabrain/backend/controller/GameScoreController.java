
package com.karnatakabrain.backend.controller;

import com.karnatakabrain.backend.entity.GameScore;
import com.karnatakabrain.backend.entity.User;
import com.karnatakabrain.backend.repository.GameScoreRepository;
import com.karnatakabrain.backend.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/scores")
@CrossOrigin(origins = "*")
public class GameScoreController {

    private final GameScoreRepository gameScoreRepository;
    private final UserRepository userRepository;

    public GameScoreController(GameScoreRepository gameScoreRepository,
                               UserRepository userRepository) {
        this.gameScoreRepository = gameScoreRepository;
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<?> saveScore(@RequestBody Map<String, Object> data) {

        Long userId = Long.valueOf(data.get("userId").toString());

        User user = userRepository.findById(userId)
                .orElse(null);

        if (user == null) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", "User not found"));
        }

        GameScore gameScore = new GameScore();

        gameScore.setUser(user);

        gameScore.setRegion1Score(
                Integer.parseInt(data.get("region1Score").toString())
        );

        gameScore.setRegion2Score(
                Integer.parseInt(data.get("region2Score").toString())
        );

        gameScore.setRegion3Score(
                Integer.parseInt(data.get("region3Score").toString())
        );

        gameScore.setRegion4Score(
                Integer.parseInt(data.get("region4Score").toString())
        );

        gameScore.setTotalScore(
                Integer.parseInt(data.get("totalScore").toString())
        );

        gameScore.setPlayedAt(LocalDateTime.now());

        GameScore savedScore = gameScoreRepository.save(gameScore);

        return ResponseEntity.ok(
                Map.of(
                        "message", "Score saved successfully",
                        "gameId", savedScore.getId(),
                        "totalScore", savedScore.getTotalScore(),
                        "playedAt", savedScore.getPlayedAt()
                )
        );
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUserScores(@PathVariable Long userId) {

        User user = userRepository.findById(userId)
                .orElse(null);

        if (user == null) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", "User not found"));
        }

        return ResponseEntity.ok(
                gameScoreRepository.findByUserIdOrderByPlayedAtDesc(userId)
        );
    }

}

