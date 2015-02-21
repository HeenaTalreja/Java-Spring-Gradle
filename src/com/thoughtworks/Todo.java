package com.thoughtworks;

public class Todo {

    private String title;
    private Boolean completed;
    private String url;

    public Todo() {
        this.completed = false;
        this.url = "";
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean isCompleted() {
        return completed;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
}
