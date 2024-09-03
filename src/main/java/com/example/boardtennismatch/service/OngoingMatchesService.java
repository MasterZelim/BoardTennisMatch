package com.example.boardtennismatch.service;

import com.example.boardtennismatch.model.MatchScore;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class OngoingMatchesService {

    private final Map<UUID,Optional<MatchScore>> scoreMap;
    public OngoingMatchesService() {
        scoreMap = new ConcurrentHashMap<>();
    }
    public Optional<MatchScore> getMatchScore(UUID uuid){
        return scoreMap.get(uuid);
    }
    public Optional<UUID> setMatchScore(MatchScore matchScore){
        scoreMap.put(matchScore.getUuidMatch(), Optional.of(matchScore));
        return Optional.ofNullable(matchScore.getUuidMatch());
    }
    public void cleanMatchMap(){
        scoreMap.clear();
    }
}
