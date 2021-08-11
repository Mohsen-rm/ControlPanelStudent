package com.ajiashi.controlpanelstudent.mode;


import com.google.firebase.database.IgnoreExtraProperties;
/**
 * Created by Ravi Tamada on 07/10/16.
 * www.androidhive.info
 */

@IgnoreExtraProperties
public class News {
    public String id;
    public String title;
    public String text;
    public String time;
    public String src;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public News() {
    }

    public News(String title, String text, String time , String src , String id) {
        this.title = title;
        this.text = text;
        this.time = time;
        this.src = src;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}