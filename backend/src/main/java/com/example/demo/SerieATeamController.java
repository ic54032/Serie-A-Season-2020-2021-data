package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileWriter;
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

    @PostMapping("/refresh")
    public void refreshData() throws IOException {
        List<TeamPlayerDTO> teamPlayerDTOs = serieATeamService.getTeamPlayerDTOs();

        // Save JSON data to file
        try (FileWriter jsonWriter = new FileWriter("C:/Users/ivanc/Radna površina/OR/Serie-A-Season-2020-2021-data/serieATeams.json")) {
            jsonWriter.write(new ObjectMapper().writeValueAsString(teamPlayerDTOs));
        }

        // Save CSV data to file
        try (FileWriter csvWriter = new FileWriter("C:/Users/ivanc/Radna površina/OR/Serie-A-Season-2020-2021-data/serieATeams.csv")) {
            StringBuilder csvBuilder = new StringBuilder();
            csvBuilder.append("teamid,team,goals,shotspg,yellow_cards,red_cards,avgpossessionperc,avgpassaccperc,aerialswon,rating,playerid,playerfirstname,playerlastname,position\n");

            for (TeamPlayerDTO dto : teamPlayerDTOs) {
                for (PlayerDTO player : dto.getPlayers()) {
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

            csvWriter.write(csvBuilder.toString());
        }
    }
}