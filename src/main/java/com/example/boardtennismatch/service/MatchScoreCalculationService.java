package com.example.boardtennismatch.service;

import com.example.boardtennismatch.model.MatchScore;
import jakarta.persistence.EntityNotFoundException;

import java.util.UUID;

public class MatchScoreCalculationService {
    private final OngoingMatchesService ongoingMatchesService;
    public MatchScoreCalculationService(OngoingMatchesService ongoingMatchesService) {
        this.ongoingMatchesService = ongoingMatchesService;
    }
    public void matchScoreCalculation(String playerWhoWonPoint, UUID uuid) {
        MatchScore matchScore = ongoingMatchesService.getMatchScore(uuid).orElseThrow(()->new EntityNotFoundException("Object matchScore not found!"));
        if (playerWhoWonPoint.equals("player1")) {
            switch (matchScore.getPlayerFirstPoints()) {
                case 0:
                    matchScore.setPlayerFirstPoints(15);
                    break;
                case 15:
                    matchScore.setPlayerFirstPoints(30);
                    break;
                case 30:
                    matchScore.setPlayerFirstPoints(40);
                    break;
                case 40:
                    int games = matchScore.getPlayerFirstGames();
                    matchScore.setPlayerFirstGames(games+1);
                    matchScore.setPlayerFirstPoints(0);
                    break;
                default:
                    break;
            }
            if (matchScore.getPlayerFirstGames()==6){
                int sets = matchScore.getPlayerFirstSets();
                matchScore.setPlayerFirstSets(sets+1);
                matchScore.setPlayerFirstGames(0);
            }
            if (matchScore.getPlayerFirstSets()==2){
                matchScore.setWinner(matchScore.getPlayerFirst());
                matchScore.setMatchEnd(true);
            }
        } else if (playerWhoWonPoint.equals("player2")) {
            switch (matchScore.getPlayerSecondPoints()) {
                case 0:
                    matchScore.setPlayerSecondPoints(15);
                    break;
                case 15:
                    matchScore.setPlayerSecondPoints(30);
                    break;
                case 30:
                    matchScore.setPlayerSecondPoints(40);
                    break;
                case 40:
                    int games = matchScore.getPlayerSecondGames();
                    matchScore.setPlayerSecondGames(games+1);
                    matchScore.setPlayerSecondPoints(0);
                    break;
                default:
                    break;
            }
            if (matchScore.getPlayerSecondGames()==6){
                int sets = matchScore.getPlayerSecondSets();
                matchScore.setPlayerSecondSets(sets+1);
                matchScore.setPlayerSecondGames(0);
            }
            if (matchScore.getPlayerSecondSets()==2){
                matchScore.setWinner(matchScore.getPlayerSecond());
                matchScore.setMatchEnd(true);
            }
        }
        ongoingMatchesService.setMatchScore(matchScore);
    }
}
