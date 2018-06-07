package com.example.ayalla.myapplication.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.graphics.Bitmap;

import java.util.Objects;

@Entity
public class TodoItem {

    @PrimaryKey(autoGenerate = true)
    private int uid;
    private String title;
    private String description;
    private Boolean isDone;

    private String image;


    public TodoItem(String title, String description, String image) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.isDone = false;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getDone() {
        return isDone;
    }

    public void setDone(Boolean done) {
        isDone = done;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TodoItem)) return false;
        TodoItem todoItem = (TodoItem) o;
        return getUid() == todoItem.getUid() &&
                Objects.equals(getTitle(), todoItem.getTitle()) &&
                Objects.equals(getDescription(), todoItem.getDescription()) &&
                Objects.equals(isDone, todoItem.isDone) &&
                Objects.equals(getImage(), todoItem.getImage());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getUid(), getTitle(), getDescription(), isDone, getImage());
    }
}
