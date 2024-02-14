package com.n11.spring.vaka.cache;

import com.n11.spring.vaka.model.Task;

import java.util.ArrayList;
import java.util.List;

public class Konferans {
    private static Konferans instance = null;
    List<Task> sabahSunumListesi = new ArrayList<>();
    List<Task> ogleSunumListesi = new ArrayList<>();
    List<Task> tumSunumListesi = new ArrayList<>();

    public void sabahSunumuEkle(Task ts) {
        sabahSunumListesi.add(ts);
    }

    public void ogleSunumuEkle(Task ts) {
        ogleSunumListesi.add(ts);
    }
    public void tumSunumEkle(Task ts) {
        tumSunumListesi.add(ts);
    }
    public static Konferans getInstance()
    {
        if (instance == null)
            instance = new Konferans();
        return instance;
    }

    public List<Task> getSabahSunumListesi() {
        return sabahSunumListesi;
    }

    public void setSabahSunumListesi(List<Task> sabahSunumListesi) {
        this.sabahSunumListesi = sabahSunumListesi;
    }

    public List<Task> getOgleSunumListesi() {
        return ogleSunumListesi;
    }

    public void setOgleSunumListesi(List<Task> ogleSunumListesi) {
        this.ogleSunumListesi = ogleSunumListesi;
    }

    public List<Task> getTumSunumListesi() {
        return tumSunumListesi;
    }

    public void setTumSunumListesi(List<Task> tumSunumListesi) {
        this.tumSunumListesi = tumSunumListesi;
    }
}
