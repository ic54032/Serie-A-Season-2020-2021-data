// TeamPlayerDTO.java
package com.example.demo;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@SqlResultSetMapping(
        name = "TeamPlayerDTOMapping",
        classes = @ConstructorResult(
                targetClass = TeamPlayerDTO.class,
                columns = {
                        @ColumnResult(name = "teamid", type = Long.class),
                        @ColumnResult(name = "team", type = String.class),
                        @ColumnResult(name = "goals", type = Integer.class),
                        @ColumnResult(name = "shotspg", type = Double.class),
                        @ColumnResult(name = "yellow_cards", type = Integer.class),
                        @ColumnResult(name = "red_cards", type = Integer.class),
                        @ColumnResult(name = "avgpossessionperc", type = Double.class),
                        @ColumnResult(name = "avgpassaccperc", type = Double.class),
                        @ColumnResult(name = "aerialswon", type = Integer.class),
                        @ColumnResult(name = "rating", type = Double.class),
                        @ColumnResult(name = "playerid", type = Long.class),
                        @ColumnResult(name = "playerfirstname", type = String.class),
                        @ColumnResult(name = "playerlastname", type = String.class),
                        @ColumnResult(name = "position", type = String.class)
                }
        )
)
public class TeamPlayerDTO {
    @Id
    private Long teamid;
    private String team;
    private int goals;
    private double shotspg;
    private int yellow_cards;
    private int red_cards;
    private double avgpossessionperc;
    private double avgpassaccperc;
    private int aerialswon;
    private double rating;
    @Transient
    private ArrayList<PlayerDTO> players;

    public TeamPlayerDTO(Long teamid, String team, int goals, double shotspg, int yellow_cards, int red_cards, double avgpossessionperc, double avgpassaccperc, int aerialswon, double rating, ArrayList<PlayerDTO> players) {
        this.teamid = teamid;
        this.team = team;
        this.goals = goals;
        this.shotspg = shotspg;
        this.yellow_cards = yellow_cards;
        this.red_cards = red_cards;
        this.avgpossessionperc = avgpossessionperc;
        this.avgpassaccperc = avgpassaccperc;
        this.aerialswon = aerialswon;
        this.rating = rating;
        this.players = players;
    }

    public TeamPlayerDTO() {
    }

    // Getters and setters

    public ArrayList<PlayerDTO> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<PlayerDTO> players) {
        this.players = players;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getAerialswon() {
        return aerialswon;
    }

    public void setAerialswon(int aerialswon) {
        this.aerialswon = aerialswon;
    }

    public double getAvgpassaccperc() {
        return avgpassaccperc;
    }

    public void setAvgpassaccperc(double avgpassaccperc) {
        this.avgpassaccperc = avgpassaccperc;
    }

    public double getAvgpossessionperc() {
        return avgpossessionperc;
    }

    public void setAvgpossessionperc(double avgpossessionperc) {
        this.avgpossessionperc = avgpossessionperc;
    }

    public int getRed_cards() {
        return red_cards;
    }

    public void setRed_cards(int red_cards) {
        this.red_cards = red_cards;
    }

    public int getYellow_cards() {
        return yellow_cards;
    }

    public void setYellow_cards(int yellow_cards) {
        this.yellow_cards = yellow_cards;
    }

    public double getShotspg() {
        return shotspg;
    }

    public void setShotspg(double shotspg) {
        this.shotspg = shotspg;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public Long getTeamid() {
        return teamid;
    }

    public void setTeamid(Long teamid) {
        this.teamid = teamid;
    }

}