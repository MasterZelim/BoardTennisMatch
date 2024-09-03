package com.example.boardtennismatch.service;

import com.example.boardtennismatch.model.Match;
import com.example.boardtennismatch.repository.MatchRepositoryImpl;
import com.example.boardtennismatch.repository.SpecMatchRepository;
import com.example.boardtennismatch.util.HibernateUtil;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.UUID;

public class FinishedMatchesPersistenceService {
    private final SpecMatchRepository<Match, Integer> matchRepository;
    private final OngoingMatchesService ongoingMatchesService;
    private final HibernateUtil hibernateUtil;

    public FinishedMatchesPersistenceService(OngoingMatchesService ongoingMatchesService) {
        this.ongoingMatchesService = ongoingMatchesService;
        this.matchRepository = new MatchRepositoryImpl();
        this.hibernateUtil = HibernateUtil.getInstance();

    }

    public void saveMatchToDB(UUID uuidMatch) {
        Match match = ongoingMatchesService.getMatchScore(uuidMatch).map((matchScore -> Match.builder()
                        .playerFirst(matchScore.getPlayerFirst())
                        .playerSecond(matchScore.getPlayerSecond())
                        .winner(matchScore.getWinner()).build()))
                .orElseThrow(() -> new EntityNotFoundException("Object match is not found in scoreModel"));

        Transaction transaction = null;

        try {
            Session session = hibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            matchRepository.save(match, session);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }

    }

    public List<Match> getAllMatch() {

        List<Match> matchList;
        Transaction transaction = null;
        try {
            Session session = hibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            matchList = matchRepository.getAllMatch(hibernateUtil.getSessionFactory().getCurrentSession());
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }

        return matchList;
    }
}

