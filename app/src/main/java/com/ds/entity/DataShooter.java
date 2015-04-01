package com.ds.entity;

/**
 * Created by aaa on 15-3-30.
 */
public class DataShooter {

    private String name;
    private String team;
    private int processNum;
    private String team_id;

    public String getTeam_id() {
        return team_id;
    }

    public void setTeam_id(String team_id) {
        this.team_id = team_id;
    }



    public DataShooter(String name, String team, int processNum) {
        this.name = name;
        this.team = team;
        this.processNum = processNum;
    }

    public DataShooter() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getProcessNum() {
        return processNum;
    }

    public void setProcessNum(int processNum) {
        this.processNum = processNum;
    }
}
