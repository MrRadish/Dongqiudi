package com.ds.entity;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.Table;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2015/3/25.
 */
@Table(name="mainarticle")
public class MainArticle implements Serializable {
    @Id(column = "id")
    private int id;
    //头条，文章标题
    @Column(column = "title")
    private String title;
    //文章描述
    @Column(column = "description")
    private String description;
    //评论数
    @Column(column = "comments")
    private int comments;
    //webview网址
    @Column(column = "shareUrl")
    private String shareUrl;
    //发表时间
    @Column(column = "publishTime")
    private String publishTime;
    //文章文本
    @Column(column = "articleurl")
    private String articleUrl;
    //标签
    @Column(column = "label")
    private String label;
    //标签颜色
    @Column(column = "labelColor")
    private String labelColor;
    //图片
    @Column(column = "imgUrl")
    private String imgUrl;
    @Column(column = "nextUrl")
    private String nextUrl;

    private List<String> sbjImgUrl;
    private int sbjImgCount;

    public int getSbjImgCount() {
        return sbjImgCount;
    }

    public void setSbjImgCount(int sbjImgCount) {
        this.sbjImgCount = sbjImgCount;
    }

    public String getNextUrl() {
        return nextUrl;
    }

    public List<String> getSbjImgUrl() {
        return sbjImgUrl;
    }

    public void setSbjImgUrl(List<String> sbjImgUrl) {
        this.sbjImgUrl = sbjImgUrl;
    }

    public void setNextUrl(String nextUrl) {
        this.nextUrl = nextUrl;
    }

    @Override
    public String toString() {
        return "MainArticle{" +
                "id=" + id +
                ", headurl='" + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", comments=" + comments +
                ", shareUrl='" + shareUrl + '\'' +
                ", publishTime='" + publishTime + '\'' +
                ", article='" + articleUrl + '\'' +
                ", label='" + label + '\'' +
                ", labelColor='" + labelColor + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
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
