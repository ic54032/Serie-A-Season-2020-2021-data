// SerieATeamService.java
package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SerieATeamService {

    @Autowired
    private CustomRepository customRepository;

    public List<TeamPlayerDTO> getTeamPlayerDTOs() {
        List<Object[]> rawResults = customRepository.findAllTeamsWithPlayersRaw();
        Map<Long, TeamPlayerDTO> teamMap = new HashMap<>();

        for (Object[] row : rawResults) {
            Long teamid = ((Number) row[0]).longValue();
            TeamPlayerDTO team = teamMap.get(teamid);

            if (team == null) {
                team = new TeamPlayerDTO(
                        teamid,
                        (String) row[1],               // team
                        ((Number) row[2]).intValue(),  // goals
                        ((Number) row[3]).doubleValue(), // shotspg
                        ((Number) row[4]).intValue(),  // yellow_cards
                        ((Number) row[5]).intValue(),  // red_cards
                        ((Number) row[6]).doubleValue(), // avgpossessionperc
                        ((Number) row[7]).doubleValue(), // avgpassaccperc
                        ((Number) row[8]).intValue(),  // aerialswon
                        ((Number) row[9]).doubleValue(), // rating
                        new ArrayList<PlayerDTO>()
                );
                teamMap.put(teamid, team);
            }

            PlayerDTO player = new PlayerDTO(
                    ((Number) row[10]).longValue(), // playerid
                    (String) row[11],              // playerfirstname
                    (String) row[12],              // playerlastname
                    (String) row[13]               // position
            );

            team.getPlayers().add(player);
        }

        return new ArrayList<>(teamMap.values());
    }
}