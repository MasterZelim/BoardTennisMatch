package com.example.boardtennismatch.repository;

import com.example.boardtennismatch.model.Match;
import com.example.boardtennismatch.util.HibernateUtil;
import org.hibernate.Session;
import java.util.List;
import java.util.Optional;

public class MatchRepositoryImpl implements SpecMatchRepository<Match, Integer> {

    @Override
    public Optional<Match> save(Match match, Session session) {
        session.save(match);
        return Optional.ofNullable(match);
    }
    @Override
    public Optional<Match> getById(Integer id, Session session) {
        return Optional.ofNullable(session.get(Match.class,id));
    }

    @Override
    public List<Match> getAllMatch(Session session) {
        return session.createQuery("from Match",Match.class).getResultList();
        }
   }

