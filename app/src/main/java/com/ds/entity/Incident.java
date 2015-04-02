package com.ds.entity;

/**
 * Created by aaa on 15-4-2.
 */
public class Incident {
    private String code;
    private String person;
    private String person_id;
    private String minute;
    private String team_id;

    public Incident() {
    }

    public Incident(String code, String person, String person_id, String minute, String team_id) {
        this.code = code;
        this.person = person;
        this.person_id = person_id;
        this.minute = minute;
        this.team_id = team_id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getPerson_id() {
        return person_id;
    }

    public void setPerson_id(String person_id) {
        this.person_id = person_id;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public String getTeam_id() {
        return team_id;
    }

    public void setTeam_id(String team_id) {
        this.team_id = team_id;
    }
}
