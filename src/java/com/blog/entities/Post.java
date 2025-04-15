package com.blog.entities;

import java.sql.Timestamp;

public class Post {
    private int id;
    private String title;
    private String content;
    private int userId;  // Ye bataayega ki post kis user ne likhi hai
    private Timestamp createdAt;

    // Constructor (Full)
    public Post(int id, String title, String content, int userId, Timestamp createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.createdAt = createdAt;
    }

    // Constructor (Without ID & Timestamp - Jab naye post create ho)
    public Post(String title, String content, int userId) {
        this.title = title;
        this.content = content;
        this.userId = userId;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
}

