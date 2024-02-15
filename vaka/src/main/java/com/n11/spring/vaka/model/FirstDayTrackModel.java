package com.n11.spring.vaka.model;

import java.util.ArrayList;
import java.util.List;

public class FirstDayTrackModel {
    private List<Task> morningTask = new ArrayList<>();
    private List<Task> afternoonTask = new ArrayList<>();

    private List<FirstDayTrackModel> firstDayTrackModelList = new ArrayList<>();

    public List<Task> getMorningTask() {
        return morningTask;
    }

    public FirstDayTrackModel(List<Task> morningTask, List<Task> afternoonTask) {
        this.morningTask.addAll(morningTask);
        this.afternoonTask.addAll(afternoonTask);
    }

    public void setMorningTask(List<Task> morningTask) {
        this.morningTask = morningTask;
    }

    public List<Task> getAfternoonTask() {
        return afternoonTask;
    }

    public void setAfternoonTask(List<Task> afternoonTask) {
        this.afternoonTask = afternoonTask;
    }

    public List<FirstDayTrackModel> getFirstDayTrackModelList() {
        return firstDayTrackModelList;
    }

    public void setFirstDayTrackModelList(List<FirstDayTrackModel> firstDayTrackModelList) {
        this.firstDayTrackModelList.addAll(firstDayTrackModelList);
    }
}
