package com.n11.spring.vaka.util;

import com.n11.spring.vaka.cache.Konferans;
import com.n11.spring.vaka.model.Task;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PlanMaker {

    public void plan() {
        Konferans.getInstance().setSabahSunumListesi(generatePlanMorning(Konferans.getInstance().getTumSunumListesi(), "09:00AM", "12:00PM"));
        int a = 10;
    }

    private List<Task> generatePlanMorning(List<Task> talks, String startTime, String endTime) {
        List<Task> track = new ArrayList<>();
        String currentTime = startTime;

        for (Task talk : talks) {
            if (currentTime.compareTo(endTime) > 0) {
                break;
            }
            if (currentTime.compareTo(endTime) == 0) {
                track.add(new Task(endTime + " Lunch", 0));
            }   else
                track.add(new Task(currentTime + " " + talk.getTitle(), talk.getDuration()));

            currentTime = incrementTime(currentTime, talk.getDuration());
        }


        return track;
    }

    private String incrementTime(String currentTime, int duration) {
        int hours = Integer.parseInt(currentTime.substring(0, 2));
        int minutes = Integer.parseInt(currentTime.substring(3, 5));

        int newMinutes = (minutes + duration) % 60;
        int newHours = hours + (minutes + duration) / 60;

        return String.format("%02d:%02d%s", newHours, newMinutes, (newHours < 12) ? "AM" : "PM");
    }
}
