package com.example.boardtennismatch.repository;
import com.example.boardtennismatch.model.Player;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class PlayerRepositoryImpl implements SpecPlayerRepository <Player,Integer>{
    @Override
    public Optional<Player> save(Player player, Session session) {
        session.save(player);
        return Optional.ofNullable(player);
    }

    @Override
    public Optional<Player> getById(Integer id, Session session) {
        return Optional.ofNullable(session.get(Player.class,id));
    }

    @Override
    public Optional<Player> getByName(String playerName, Session session) {
           return session.createQuery("select p from Player p where p.name=:name",Player.class).setParameter("name", playerName).uniqueResultOptional();
    }
}
