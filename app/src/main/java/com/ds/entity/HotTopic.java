package com.ds.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aaa on 15-3-26.
 */
public class HotTopic {
    private List<Attachment>attachments;
    private int attachments_total;
    private Member author;
    private String content;
    private String created_at;
    private League group;
    private String id;
    private String title;      //标题
    private int total_replies;  //总回复数


    public int getTotal_replies() {
        return total_replies;
    }

    public void setTotal_replies(int total_replies) {
        this.total_replies = total_replies;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public int getAttachments_total() {
        return attachments_total;
    }

    public void setAttachments_total(int attachments_total) {
        this.attachments_total = attachments_total;
    }

    public Member getAuthor() {
        return author;
    }

    public void setAuthor(Member author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public League getGroup() {
        return group;
    }

    public void setGroup(League group) {
        this.group = group;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "HotTopic{" +
                "attachments=" + attachments +
                ", attachments_total=" + attachments_total +
                ", author=" + author +
                ", content='" + content + '\'' +
                ", created_at='" + created_at + '\'' +
                ", group=" + group +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", total_replies=" + total_replies +
                '}';
    }
}
