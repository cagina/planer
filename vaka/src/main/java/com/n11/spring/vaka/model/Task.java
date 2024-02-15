package com.n11.spring.vaka.model;

public class Task {
    String title;
    int duration; // in minutes

    public Task(String title, int duration) {
        this.title = title;
        this.duration = duration;
    }

    @Override
    public String toString() {
        return title + " " + (duration != 0 ? duration + "min": "");
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
