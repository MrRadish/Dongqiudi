package com.ds.entity;

/**
 * Created by aaa on 15-3-31.
 */
public class Comment {

    private String content;
    private int floor;
    private String created_at;
    private Member author;

    public Member getAuthor() {
        return author;
    }

    public void setAuthor(Member author) {
        this.author = author;
    }

    public Comment() {
    }

    public Comment(String content, int floor, String created_at) {
        this.content = content;
        this.floor = floor;
        this.created_at = created_at;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "content='" + content + '\'' +
                ", floor=" + floor +
                ", created_at='" + created_at + '\'' +
                ", author=" + author +
                '}';
    }
}
