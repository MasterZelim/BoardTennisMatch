package com.example.boardtennismatch.service;

import com.example.boardtennismatch.model.MatchScore;
import com.example.boardtennismatch.model.Player;
import com.example.boardtennismatch.repository.PlayerRepositoryImpl;
import com.example.boardtennismatch.repository.SpecPlayerRepository;
import com.example.boardtennismatch.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Optional;
import java.util.UUID;

public class GenerationMatchScoreService {

    private final OngoingMatchesService ongoingMatchesService;
    private final SpecPlayerRepository<Player, Integer> playerRepository;
    private final HibernateUtil hibernateUtil;

    public GenerationMatchScoreService(OngoingMatchesService ongoingMatchesService) {
        this.ongoingMatchesService = ongoingMatchesService;
        this.playerRepository = new PlayerRepositoryImpl();
        hibernateUtil = HibernateUtil.getInstance();
    }

    public Optional<UUID> createNewMatchScores(String firstPlayerName, String secondPlayerName) {
        MatchScore matchScore = MatchScore.builder()
                .playerFirst(getOrCreatePlayer(firstPlayerName))
                .playerSecond(getOrCreatePlayer(secondPlayerName))
                .uuidMatch(getRandomUUID())
                .build();

        return ongoingMatchesService.setMatchScore(matchScore);
    }

    private Player getOrCreatePlayer(String playerName) {
        Transaction transaction = null;
        Player player = null;
        try {
            Session session = hibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            player = playerRepository.getByName(playerName, session)
                                     .orElseGet(() -> playerRepository.save(Player.builder().name(playerName).build(), session)
                                     .orElseThrow(() -> new RuntimeException("Failed to save player!")));
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
        return player;
    }

    private UUID getRandomUUID() {
        return UUID.randomUUID();
    }
}
