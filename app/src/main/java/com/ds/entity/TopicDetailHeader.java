package com.ds.entity;

import java.security.acl.Group;
import java.util.List;

/**
 * Created by aaa on 15-3-30.
 */
public class TopicDetailHeader {
    private String title;
    private String content;
    private String created_at;
    private int total_replies;
    private int id;


    private League group;
    private Member author;

    private List<Member>last_up_users;

    private List<Attachment>attachments;

    public TopicDetailHeader() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public int getTotal_replies() {
        return total_replies;
    }

    public void setTotal_replies(int total_replies) {
        this.total_replies = total_replies;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public League getGroup() {
        return group;
    }

    public void setGroup(League group) {
        this.group = group;
    }

    public Member getAuthor() {
        return author;
    }

    public void setAuthor(Member author) {
        this.author = author;
    }

    public List<Member> getLast_up_users() {
        return last_up_users;
    }

    public void setLast_up_users(List<Member> last_up_users) {
        this.last_up_users = last_up_users;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public TopicDetailHeader(String title, String content, String created_at, int total_replies, int id, League group, Member author, List<Member> last_up_users, List<Attachment> attachments) {
        this.title = title;
        this.content = content;
        this.created_at = created_at;
        this.total_replies = total_replies;
        this.id = id;
        this.group = group;
        this.author = author;
        this.last_up_users = last_up_users;
        this.attachments = attachments;
    }

    @Override
    public String toString() {
        return "TopicDetailHeader{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", created_at='" + created_at + '\'' +
                ", total_replies=" + total_replies +
                ", id=" + id +
                ", group=" + group +
                ", author=" + author +
                ", last_up_users=" + last_up_users +
                ", attachments=" + attachments +
                '}';
    }
}
