package com.n11.spring.vaka.cache;

import com.n11.spring.vaka.model.Task;

import java.util.ArrayList;
import java.util.List;

public class  Konferans {
    private static Konferans instance = null;
    List<Task> tumSunumListesi = new ArrayList<>();
    private StringBuilder trackResult = new StringBuilder();
    private StringBuilder trackResultTwoDays = new StringBuilder();
    public void tumSunumEkle(Task ts) {
        tumSunumListesi.add(ts);
    }
    public static Konferans getInstance()
    {
        if (instance == null)
            instance = new Konferans();
        return instance;
    }

    public List<Task> getTumSunumListesi() {
        return tumSunumListesi;
    }
    public void setTumSunumListesi(List<Task> tumSunumListesi) {
        this.tumSunumListesi = tumSunumListesi;
    }

    public StringBuilder getTrackResult() {
        return trackResult;
    }

    public void setTrackResult(StringBuilder trackResult) {
        this.trackResult = trackResult;
    }

    public StringBuilder getTrackResultTwoDays() {
        return trackResultTwoDays;
    }

    public void setTrackResultTwoDays(StringBuilder trackResultTwoDays) {
        this.trackResultTwoDays = trackResultTwoDays;
    }
}
