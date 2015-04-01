package com.ds.entity;

/**
 * Created by aaa on 15-3-27.
 */
public class DataIntegral {
    private String img_id;
    private String ranking;

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    public String getImg_id() {
        return img_id;
    }

    public void setImg_id(String img_id) {
        this.img_id = img_id;
    }

    private String team;
    private int matcheTotal;
    private int victory;
    private int draw;
    private int lost;
    private int process;
    private int lose;
    private int point;

    public DataIntegral() {
    }

    public DataIntegral(String team, int matcheTotal, int victory, int draw, int lost, int process, int lose, int point) {
        this.team = team;
        this.matcheTotal = matcheTotal;
        this.victory = victory;
        this.draw = draw;
        this.lost = lost;
        this.process = process;
        this.lose = lose;
        this.point = point;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getMatcheTotal() {
        return matcheTotal;
    }

    public void setMatcheTotal(int matcheTotal) {
        this.matcheTotal = matcheTotal;
    }

    public int getVictory() {
        return victory;
    }

    public void setVictory(int victory) {
        this.victory = victory;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getLost() {
        return lost;
    }

    public void setLost(int lost) {
        this.lost = lost;
    }

    public int getProcess() {
        return process;
    }

    public void setProcess(int process) {
        this.process = process;
    }

    public int getLose() {
        return lose;
    }

    public void setLose(int lose) {
        this.lose = lose;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
