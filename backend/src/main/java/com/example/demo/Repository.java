package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import jakarta.persistence.QueryHint;
import java.util.List;

public interface Repository extends JpaRepository<TeamPlayerDTO, Long> {
    @Query(value = "SELECT * FROM teams NATURAL JOIN players", nativeQuery = true)
    @QueryHints(@QueryHint(name = "org.hibernate.annotations.QueryHints.RESULT_SET_MAPPING", value = "TeamPlayerDTOMapping"))
    List<TeamPlayerDTO> findAllTeamsWithPlayers();
}