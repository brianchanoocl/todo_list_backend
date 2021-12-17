package com.example.todoList.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class TodoItem {
    private String id;
    private String text;
    private Boolean done;

    public TodoItem(String id, String text, Boolean done) {
        this.id = id;
        this.text = text;
        this.done = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean isDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }
}
