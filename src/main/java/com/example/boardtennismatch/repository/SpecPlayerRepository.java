package com.example.boardtennismatch.repository;

import com.example.boardtennismatch.model.Player;
import org.hibernate.Session;
import java.io.Serializable;
import java.util.Optional;

public interface SpecPlayerRepository<E,K extends Serializable> extends Repository<E,K> {
    Optional<E> getByName(String name, Session session);
}
