package com.example.demo;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/teams")
public class SerieATeamController {

    @Autowired
    private SerieATeamService serieATeamService;

    @GetMapping("/json")
    public List<TeamPlayerDTO> getAllTeamsWithPlayersAsJson() {
        return serieATeamService.getTeamPlayerDTOs();
    }

    @GetMapping("/csv")
    public void getAllTeamsWithPlayersAsCsv(HttpServletResponse response) throws IOException {
        List<TeamPlayerDTO> teamPlayerDTOs = serieATeamService.getTeamPlayerDTOs();

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"teams_players.csv\"");

        StringBuilder csvBuilder = new StringBuilder();
        csvBuilder.append("teamid,team,goals,shotspg,yellow_cards,red_cards,avgpossessionperc,avgpassaccperc,aerialswon,rating,playerid,playerfirstname,playerlastname,position\n");

        for (TeamPlayerDTO dto : teamPlayerDTOs) {
            for(PlayerDTO player : dto.getPlayers()) {
                csvBuilder.append(dto.getTeamid()).append(",")
                        .append(dto.getTeam()).append(",")
                        .append(dto.getGoals()).append(",")
                        .append(dto.getShotspg()).append(",")
                        .append(dto.getYellow_cards()).append(",")
                        .append(dto.getRed_cards()).append(",")
                        .append(dto.getAvgpossessionperc()).append(",")
                        .append(dto.getAvgpassaccperc()).append(",")
                        .append(dto.getAerialswon()).append(",")
                        .append(dto.getRating()).append(",")
                        .append(player.getPlayerid()).append(",")
                        .append(player.getPlayerfirstname()).append(",")
                        .append(player.getPlayerlastname()).append(",")
                        .append(player.getPosition()).append("\n");
            }
        }

        response.getWriter().write(csvBuilder.toString());
    }
}