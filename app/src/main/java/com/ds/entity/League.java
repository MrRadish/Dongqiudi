package com.ds.entity;

/**
 * Created by aaa on 15-3-26.
 */
public class League {
    private int id;
    private String describle;   //描述有多少人加入了圈子
    private String type;
    private String title;      //圈名
    private String thumb;       //缩略图
    private int topic_total;
    private int join_user_total;
    private int league_id;   //所在联盟的id值

    public int getLeague_id() {
        return league_id;
    }

    public void setLeague_id(int league_id) {
        this.league_id = league_id;
    }

    public int getTopic_total() {
        return topic_total;
    }

    public void setTopic_total(int topic_total) {
        this.topic_total = topic_total;
    }

    public int getJoin_user_total() {
        return join_user_total;
    }

    public void setJoin_user_total(int join_user_total) {
        this.join_user_total = join_user_total;
    }

    public League(){

    }

    public League(int id, String describle, String type, String title, String thumb) {
        this.id = id;
        this.describle = describle;
        this.type = type;
        this.title = title;
        this.thumb = thumb;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescrible() {
        return describle;
    }

    public void setDescrible(String describle) {
        this.describle = describle;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    @Override
    public String toString() {
        return "League{" +
                "id=" + id +
                ", describle='" + describle + '\'' +
                ", type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", thumb='" + thumb + '\'' +
                ", topic_total=" + topic_total +
                ", join_user_total=" + join_user_total +
                '}';
    }
}
