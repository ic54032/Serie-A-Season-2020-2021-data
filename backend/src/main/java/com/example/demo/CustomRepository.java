package com.example.demo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Object[]> findAllTeamsWithPlayersRaw() {
        String sql = "SELECT * FROM teams NATURAL JOIN players";
        return entityManager.createNativeQuery(sql).getResultList();
    }
}