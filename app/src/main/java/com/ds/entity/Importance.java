package com.ds.entity;

/**
 * Created by aaa on 15-3-26.
 */
public class Importance {
    private String team_A_id;
    private String team_B_id;
    private String team_A_name;
    private String team_B_name;
    private String date_utc;
    private String competition_name;
    private String fs_A;
    private String fs_B;
    private String match_id;

    public Importance() {
    }

    public Importance(String team_A_id, String team_B_id, String team_A_name, String team_B_name, String date_utc, String competition_name, String fs_A, String fs_B) {

        this.team_A_id = team_A_id;
        this.team_B_id = team_B_id;
        this.team_A_name = team_A_name;
        this.team_B_name = team_B_name;
        this.date_utc = date_utc;
        this.competition_name = competition_name;
        this.fs_A = fs_A;
        this.fs_B = fs_B;
    }

    public String getTeam_A_id() {
        return team_A_id;
    }

    public void setTeam_A_id(String team_A_id) {
        this.team_A_id = team_A_id;
    }

    public String getTeam_B_id() {
        return team_B_id;
    }

    public void setTeam_B_id(String team_B_id) {
        this.team_B_id = team_B_id;
    }

    public String getTeam_A_name() {
        return team_A_name;
    }

    public void setTeam_A_name(String team_A_name) {
        this.team_A_name = team_A_name;
    }

    public String getTeam_B_name() {
        return team_B_name;
    }

    public void setTeam_B_name(String team_B_name) {
        this.team_B_name = team_B_name;
    }

    public String getDate_utc() {
        return date_utc;
    }

    public void setDate_utc(String date_utc) {
        this.date_utc = date_utc;
    }

    public String getCompetition_name() {
        return competition_name;
    }

    public void setCompetition_name(String competition_name) {
        this.competition_name = competition_name;
    }

    public String getFs_A() {
        return fs_A;
    }

    public void setFs_A(String fs_A) {
        this.fs_A = fs_A;
    }

    public String getFs_B() {
        return fs_B;
    }

    public void setFs_B(String fs_B) {
        this.fs_B = fs_B;
    }

    public String getMatch_id() {
        return match_id;
    }

    public void setMatch_id(String match_id) {
        this.match_id = match_id;
    }
}
