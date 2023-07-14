package com.dazuizui.business.domain;

import java.io.Serializable;

/**
 * 比赛队伍信息
 */
public class TeamInfo implements Serializable {
    private Long id;
    private String team;    //队伍名字
    private String player;  //参赛人员

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public TeamInfo() {
    }

    public TeamInfo(Long id, String team, String player) {
        this.id = id;
        this.team = team;
        this.player = player;
    }
}
