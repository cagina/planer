package com.n11.spring.vaka.model;

import java.util.ArrayList;
import java.util.List;

public class MorningTrackList {
    private List<Task> morning = new ArrayList<>();

    public MorningTrackList(List<Task> morning) {
        this.morning.addAll(morning);
    }

    public List<Task> getMorning() {
        return morning;
    }

    public void setMorning(List<Task> morning) {
        this.morning = morning;
    }
}
