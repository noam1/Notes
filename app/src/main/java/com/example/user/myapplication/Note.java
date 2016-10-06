package com.example.user.myapplication;

/**
 * Created by User on 06/10/2016.
 */
public class Note
{
    String title;
    String content;
    String id;

    public Note(String title, String content, String id) {
        this.title = title;
        this.content = content;
        this.id = id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
