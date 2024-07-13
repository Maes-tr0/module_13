package com.example.UserManagement;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Comment {
    @JsonProperty("postId")
    private int postID;
    private int ID;
    private String name;
    private String email;
    private String body;

    // Constructors
    public Comment() {}

    public Comment(int postID, int ID, String name, String email, String body) {
        this.postID = postID;
        this.ID = ID;
        this.name = name;
        this.email = email;
        this.body = body;
    }

    // Getters and Setters
    public int getPostID() {
        return postID;
    }
    public void setPostID(int postID) {
        this.postID = postID;
    }

    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "postID=" + postID +
                ", ID=" + ID +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
