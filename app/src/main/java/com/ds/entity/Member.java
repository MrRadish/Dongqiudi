package com.ds.entity;

/**
 * Created by aaa on 15-3-26.
 */
public class Member {
    private String id;
    private String username;
    private String medal_desc;    //成员级别
    private String medal_id;
    private String avatar;        //头像

    public Member(){

    }

    public Member(String id, String username, String medal_desc, String medal_id, String avatar) {
        this.id = id;
        this.username = username;
        this.medal_desc = medal_desc;
        this.medal_id = medal_id;
        this.avatar = avatar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMedal_desc() {
        return medal_desc;
    }

    public void setMedal_desc(String medal_desc) {
        this.medal_desc = medal_desc;
    }

    public String getMedal_id() {
        return medal_id;
    }

    public void setMedal_id(String medal_id) {
        this.medal_id = medal_id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", medal_desc='" + medal_desc + '\'' +
                ", medal_id='" + medal_id + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
