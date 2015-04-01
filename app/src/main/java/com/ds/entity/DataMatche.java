package com.ds.entity;

/**
 * Created by aaa on 15-3-30.
 */
public class DataMatche {
    private String date;
    private String time;
    private String aName;
    private String aImage;
    private  String aScore;
    private String bScore;

    public String getbScore() {
        return bScore;
    }

    public void setbScore(String bScore) {
        this.bScore = bScore;
    }

    private String bName;
    private String bimage;

    public DataMatche() {
    }

    public DataMatche(String date, String time, String aName, String aImage, String aScore, String bName, String bimage) {
        this.date = date;
        this.time = time;
        this.aName = aName;
        this.aImage = aImage;
        this.aScore = aScore;
        this.bName = bName;
        this.bimage = bimage;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getaName() {
        return aName;
    }

    public void setaName(String aName) {
        this.aName = aName;
    }

    public String getaImage() {
        return aImage;
    }

    public void setaImage(String aImage) {
        this.aImage = aImage;
    }

    public String getaScore() {
        return aScore;
    }

    public void setaScore(String aScore) {
        this.aScore = aScore;
    }

    public String getbName() {
        return bName;
    }

    public void setbName(String bName) {
        this.bName = bName;
    }

    public String getBimage() {
        return bimage;
    }

    public void setBimage(String bimage) {
        this.bimage = bimage;
    }
}
