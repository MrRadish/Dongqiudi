package com.ds.entity;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.Table;

import java.io.Serializable;

/**
 * 显示头条列表头部内容
 * Created by Administrator on 2015/3/26.
 */
@Table(name = "articlehead")
public class ArticleHead implements Serializable {
    @Id(column = "id")
    private int id;
    @Column(column = "headUrl")
    private String headUrl;
    @Column(column = "title")
    private String title;
    @Column(column = "imgUrl")
    private String imgUrl;
    @Column(column = "shareUrl")
    private String shareUrl;
    @Column(column = "comments")
    private int comments;
    @Column(column = "publishTime")
    private String publishTime;
    //文章文本
    @Column(column = "article")
    private String article;
    //标签
    @Column(column = "label")
    private String label;
    //标签颜色
    @Column(column = "labelColor")
    private String labelColor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ArticleHead{" +
                "headUrl='" + headUrl + '\'' +
                ", title='" + title + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", shareUrl='" + shareUrl + '\'' +
                ", comments=" + comments +
                ", publishTime='" + publishTime + '\'' +
                ", article='" + article + '\'' +
                ", label='" + label + '\'' +
                ", labelColor='" + labelColor + '\'' +
                '}';
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLabelColor() {
        return labelColor;
    }

    public void setLabelColor(String labelColor) {
        this.labelColor = labelColor;
    }
}
