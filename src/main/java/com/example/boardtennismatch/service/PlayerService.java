package com.example.boardtennismatch.service;

import com.example.boardtennismatch.model.Player;
import com.example.boardtennismatch.repository.PlayerRepositoryImpl;
import com.example.boardtennismatch.repository.SpecPlayerRepository;
import com.example.boardtennismatch.util.HibernateUtil;
import org.hibernate.Session;

import java.util.Optional;

public class PlayerService {
    private final SpecPlayerRepository<Player,Integer> playerRepository;
    private final HibernateUtil hibernateUtil;
    public PlayerService() {
        playerRepository=new PlayerRepositoryImpl();
        hibernateUtil = HibernateUtil.getInstance();
    }
    public boolean checkPlayerExists(String playerName) {
            Session session = hibernateUtil.getSessionFactory().getCurrentSession();
            return playerRepository.getByName(playerName,session).isPresent();
        }

    public void registerNewPlayer(String name) {
        if (!checkPlayerExists(name)) {
            Player player = new Player();
            player.setName(name);
            Session session = hibernateUtil.getSessionFactory().getCurrentSession();
                playerRepository.save(player,session);

        } else {
            System.out.println("Игрок с таким именем уже существует.");
        }
    }
    public Optional<Player> getPlayer(String playerName){
        Session session = hibernateUtil.getSessionFactory().getCurrentSession();
            return playerRepository.getByName(playerName,session);

        }

    }


