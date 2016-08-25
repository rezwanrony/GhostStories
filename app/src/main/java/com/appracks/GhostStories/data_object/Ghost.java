package com.appracks.GhostStories.data_object;

import java.io.Serializable;

/**
 * Created by rezwan on 8/25/2016.
 */
public class Ghost implements Serializable {
    private int id;
    private String title;
    private String content;
    private String category;

    public Ghost(int id, String title, String content, String category) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.category = category;
    }





    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
