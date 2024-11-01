package com.example.demo;

public class PlayerDTO {
    private Long playerid;
    private String playerfirstname;
    private String playerlastname;
    private String position;

    public PlayerDTO(Long playerid, String playerfirstname, String playerlastname, String position) {
        this.playerid = playerid;
        this.playerfirstname = playerfirstname;
        this.playerlastname = playerlastname;
        this.position = position;
    }

    public PlayerDTO() {
    }

    public Long getPlayerid() {
        return playerid;
    }

    public void setPlayerid(Long playerid) {
        this.playerid = playerid;
    }

    public String getPlayerfirstname() {
        return playerfirstname;
    }

    public void setPlayerfirstname(String playerfirstname) {
        this.playerfirstname = playerfirstname;
    }

    public String getPlayerlastname() {
        return playerlastname;
    }

    public void setPlayerlastname(String playerlastname) {
        this.playerlastname = playerlastname;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}