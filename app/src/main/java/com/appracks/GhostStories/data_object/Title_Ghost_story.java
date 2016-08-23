package com.appracks.GhostStories.data_object;

/**
 * Created by rezwan on 8/22/2016.
 */
public class Title_Ghost_story {
    int id;
    String title;

    public Title_Ghost_story(String title, int id) {
        this.title = title;
        this.id = id;
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
}
