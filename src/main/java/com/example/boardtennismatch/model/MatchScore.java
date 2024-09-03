package com.example.boardtennismatch.model;

import lombok.*;

import java.util.UUID;


@AllArgsConstructor
@Getter
@Setter
@Builder

public class MatchScore {
    private  UUID uuidMatch;
    private  Player playerFirst;
    private  Player playerSecond;
    private  Player winner;
    private  Player playerWhoWonPoint;
    private int playerFirstPoints;
    private int playerSecondPoints;
    private int playerFirstGames;
    private int playerSecondGames;
    private int playerFirstSets;
    private int playerSecondSets;
    private boolean isMatchEnd;

}
