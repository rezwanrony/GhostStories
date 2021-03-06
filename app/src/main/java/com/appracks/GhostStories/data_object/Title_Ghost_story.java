package com.appracks.GhostStories.data_object;

/**
 * Created by rezwan on 8/22/2016.
 */
public class Title_Ghost_story {
    private int id;
    private String title;
    private String content;
    private String category;
    private int isBookmarked;

    public Title_Ghost_story(int id, String title, String content, String category, int isBookmarked) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.category = category;
        this.isBookmarked = isBookmarked;
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

    public int getIsBookmarked() {
        return isBookmarked;
    }

    public void setIsBookmarked(int isBookmarked) {
        this.isBookmarked = isBookmarked;
    }
}
