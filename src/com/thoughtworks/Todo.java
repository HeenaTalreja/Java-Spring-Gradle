package com.thoughtworks;

public class Todo {

    private String title;
    private boolean completed;
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

    public boolean isCompleted() {
        return completed;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
