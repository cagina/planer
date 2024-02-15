package com.n11.spring.vaka.model;

import java.util.ArrayList;
import java.util.List;

public class AfterNoonTrackList {
    private List<Task> afternoon = new ArrayList<>();
    private int morningTrack;

    public List<Task> getAfternoon() {
        return afternoon;
    }

    public void setAfternoon(List<Task> afternoon) {
        this.afternoon = afternoon;
    }

    public int getMorningTrack() {
        return morningTrack;
    }

    public void setMorningTrack(int morningTrack) {
        this.morningTrack = morningTrack;
    }

    public AfterNoonTrackList(List<Task> afternoon, int morningTrack) {
        this.afternoon.addAll(afternoon);
        this.morningTrack = morningTrack;
    }

    public void setAfterNoon(Task afternoon) {
        this.afternoon.add(afternoon);
    }

}
