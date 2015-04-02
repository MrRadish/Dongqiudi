package com.ds.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/4/2.
 */
public class SubjectArticle implements Serializable {
    private int id;
    private String lable;
    private String title;
    private String description;
    private String imgUrl;
    private String publishTime;
    private String api;

    @Override
    public String toString() {
        return "SubjectArticle{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", publishTime='" + publishTime + '\'' +
                ", api='" + api + '\'' +
                '}';
    }

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }
}
