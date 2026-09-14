
package com.karnatakabrain.backend.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "game_scores")
public class GameScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private int region1Score;
    private int region2Score;
    private int region3Score;
    private int region4Score;
    private int totalScore;

    private LocalDateTime playedAt;

    public GameScore() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getRegion1Score() {
        return region1Score;
    }

    public void setRegion1Score(int region1Score) {
        this.region1Score = region1Score;
    }

    public int getRegion2Score() {
        return region2Score;
    }

    public void setRegion2Score(int region2Score) {
        this.region2Score = region2Score;
    }

    public int getRegion3Score() {
        return region3Score;
    }

    public void setRegion3Score(int region3Score) {
        this.region3Score = region3Score;
    }

    public int getRegion4Score() {
        return region4Score;
    }

    public void setRegion4Score(int region4Score) {
        this.region4Score = region4Score;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public LocalDateTime getPlayedAt() {
        return playedAt;
    }

    public void setPlayedAt(LocalDateTime playedAt) {
        this.playedAt = playedAt;
    }
}

