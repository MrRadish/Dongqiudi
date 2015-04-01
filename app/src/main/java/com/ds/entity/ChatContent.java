package com.ds.entity;

/**
 * Created by aaa on 15-3-31.
 */
public class ChatContent {
    private String message;
    private String username;
    private String avatar;

    public ChatContent() {
    }

    public ChatContent(String message, String username, String avatar) {
        this.message = message;
        this.username = username;
        this.avatar = avatar;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
