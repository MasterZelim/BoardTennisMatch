package com.example.boardtennismatch.service;

import com.example.boardtennismatch.model.Match;
import com.example.boardtennismatch.model.MatchScore;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class MainService {

    private final OngoingMatchesService ongoingMatchesService;
    private final FinishedMatchesPersistenceService finishedMatchesPersistenceService;
    private final MatchScoreCalculationService matchScoreCalculationService;
    private final GenerationMatchScoreService generationMatchScoreService;

    public MainService() {
        this.ongoingMatchesService = new OngoingMatchesService();
        this.finishedMatchesPersistenceService = new FinishedMatchesPersistenceService(ongoingMatchesService);
        this.matchScoreCalculationService = new MatchScoreCalculationService(ongoingMatchesService);
        this.generationMatchScoreService = new GenerationMatchScoreService(ongoingMatchesService);
    }

    public Optional<MatchScore> gameScoring(String playerWhoWonPoint, UUID uuid) {
        matchScoreCalculationService.matchScoreCalculation( playerWhoWonPoint, uuid);
        Optional<MatchScore> matchScore = getMatchScore(uuid);
        matchScore.filter(MatchScore::isMatchEnd)
                .ifPresent(ms -> {
                    finishedMatchesPersistenceService.saveMatchToDB(uuid);
                    ongoingMatchesService.cleanMatchMap();
                });
        return matchScore;
    }
    public UUID generationMatchScoreService(String firstPlayerName, String secondPlayerName) {
        return generationMatchScoreService.createNewMatchScores(firstPlayerName, secondPlayerName)
                .orElseThrow(() -> new IllegalArgumentException("Uuid match is not found"));
    }

    public Optional<MatchScore> getMatchScore(UUID uuidMatch) {
        return ongoingMatchesService.getMatchScore(uuidMatch);
    }

    public List<Match> getAllMatch() {
      return finishedMatchesPersistenceService.getAllMatch();
    }
}
