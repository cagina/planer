package com.n11.spring.vaka.util;

import com.n11.spring.vaka.cache.Konferans;
import com.n11.spring.vaka.model.AfterNoonTrackList;
import com.n11.spring.vaka.model.FirstDayTrackModel;
import com.n11.spring.vaka.model.MorningTrackList;
import com.n11.spring.vaka.model.Task;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PlanMaker {

    // Knapsack Probleminden esinlenilmistir.
    public void plan() {
        List<Task> cloneTumSunumlar = new ArrayList<>(Konferans.getInstance().getTumSunumListesi());
        List<FirstDayTrackModel> trackL = dayPlanner(cloneTumSunumlar, true, 0);
        Konferans.getInstance().getTrackResult().append("Second Day TrackList"+System.getProperty("line.separator"));
        for(int i = 0 ; i < trackL.size() ; i++) {
            cloneTumSunumlar = new ArrayList<>(Konferans.getInstance().getTumSunumListesi());
            cloneTumSunumlar.removeAll(trackL.get(i).getMorningTask());
            cloneTumSunumlar.removeAll(trackL.get(i).getAfternoonTask());
            trackL.get(i).setFirstDayTrackModelList(dayPlanner(cloneTumSunumlar, false, i));
        }
        for(int i = 0 ; i < trackL.size() ; i++) {
            if (trackL.get(i).getFirstDayTrackModelList() != null && !trackL.get(i).getFirstDayTrackModelList().isEmpty()) {
                Konferans.getInstance().getTrackResultTwoDays().append(generatePlan(trackL.get(i).getMorningTask(), "09:00AM", "12:00PM", true, i));
                Konferans.getInstance().getTrackResultTwoDays().append(generatePlan(trackL.get(i).getAfternoonTask(), "01:00PM", "05:00PM", false, i));
                Konferans.getInstance().getTrackResultTwoDays().append("Second Day Track List\n");
                for(int j = 0 ; j < trackL.get(i).getFirstDayTrackModelList().size() ; j++) {
                    Konferans.getInstance().getTrackResultTwoDays().append(generatePlan(trackL.get(i).getFirstDayTrackModelList().get(j).getMorningTask(), "09:00AM", "12:00PM", true, j));
                    Konferans.getInstance().getTrackResultTwoDays().append(generatePlan(trackL.get(i).getFirstDayTrackModelList().get(j).getAfternoonTask(), "01:00PM", "05:00PM", false, j));
                }
                Konferans.getInstance().getTrackResultTwoDays().append("--------------------------------------------------------\n\n");
            }
        }
    }

    private List<FirstDayTrackModel> dayPlanner(List<Task> sunumlar, boolean firstDay, int firstDayIndex) {
        int morningTime;
        StringBuilder indexs;
        String[] splt;
        int afternoonTime = 240;
        List<MorningTrackList> morningTrackList = new ArrayList<>();
        List<AfterNoonTrackList> afterNoonTrackList = new ArrayList<>();
        List<Task> tmpTask = new ArrayList<>();
        Task tmp;


        // Sabah sunumlarını hesaplama 180 dakikayı tamamlayan sunumlar
        for (int i = 0 ; i < sunumlar.size()-1 ; i++) {
            indexs = new StringBuilder();
            morningTime = 180;
            morningTime -=  sunumlar.get(i).getDuration();
            indexs.append(i).append("-");
            for (int j = i+1 ; j < sunumlar.size() ; j++) {
                tmp = sunumlar.get(j);
                if (morningTime - tmp.getDuration() >= 0) {
                    indexs.append(j).append("-");
                    morningTime -= tmp.getDuration();
                    if (morningTime == 0) {
                        splt = indexs.toString().split("-");
                        tmpTask.clear();
                        for (String index : splt) {
                            try {
                                tmpTask.add( sunumlar.get(Integer.parseInt(index)));
                            } catch (Exception e) {}
                        }
                        morningTrackList.add(new MorningTrackList(tmpTask));
                        break;
                    }
                }
            }
        }
        List<Task> cloneTumSunumlar = new ArrayList<>();

        // Ogleden sonrası sunumlarını hesaplama 240 dakikayı tamamlayan sunumlar
        int morgingIndex = 0;
        for (MorningTrackList trck : morningTrackList) {
            cloneTumSunumlar = new ArrayList<>(sunumlar);
            for (Task tk: trck.getMorning()) {
                cloneTumSunumlar.remove(tk);
            }
            for (int i = 0 ; i < cloneTumSunumlar.size()-1 ; i++) {
                indexs = new StringBuilder();
                afternoonTime = 240;
                afternoonTime -=  cloneTumSunumlar.get(i).getDuration();
                indexs.append(i).append("-");
                for (int j = i+1 ; j < cloneTumSunumlar.size() ; j++) {
                    tmp = cloneTumSunumlar.get(j);
                    if (afternoonTime - tmp.getDuration() >= 0) {
                        indexs.append(j).append("-");
                        afternoonTime -= tmp.getDuration();
                        if (afternoonTime == 0) {
                            splt = indexs.toString().split("-");
                            tmpTask.clear();
                            for (String index : splt) {
                                try {
                                    tmpTask.add(cloneTumSunumlar.get(Integer.parseInt(index)));
                                } catch (Exception e) {}
                            }
                            afterNoonTrackList.add(new AfterNoonTrackList(tmpTask, morgingIndex));
                            break;
                        } else if (afternoonTime <= 60 && afternoonTime > 0) {
                            for (int a = 0 ; a < cloneTumSunumlar.size() ; a++) {
                                if (indexs.indexOf(a+"-") < 0 && cloneTumSunumlar.get(a).getDuration() == 5) {
                                    indexs.append(a).append("-");
                                    splt = indexs.toString().split("-");
                                    tmpTask.clear();
                                    for (String index : splt) {
                                        try {
                                            tmpTask.add(cloneTumSunumlar.get(Integer.parseInt(index)));
                                        } catch (Exception e) {}
                                    }
                                    afterNoonTrackList.add(new AfterNoonTrackList(tmpTask, morgingIndex));
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            ++morgingIndex;
        }

        if (firstDay)
            Konferans.getInstance().getTrackResult().setLength(0);
        int ttmp = 0;
        List<FirstDayTrackModel> firstDayTrackModelList = new ArrayList<>();
        if (firstDay)
            Konferans.getInstance().getTrackResult().append("First Day TrackList"+System.getProperty("line.separator"));

        for (int i = 0 ; i < morningTrackList.size() ; i++) {
            for(AfterNoonTrackList trcList: afterNoonTrackList) {
                if (trcList.getMorningTrack() == i) {
                    Konferans.getInstance().getTrackResult().append(generatePlan(morningTrackList.get(i).getMorning(), "09:00AM", "12:00PM", true, firstDay ? ttmp : firstDayIndex));
                    Konferans.getInstance().getTrackResult().append(generatePlan(trcList.getAfternoon(), "01:00PM", "05:00PM", false, firstDay ? ttmp : firstDayIndex));
                    firstDayTrackModelList.add(new FirstDayTrackModel(morningTrackList.get(i).getMorning(), trcList.getAfternoon()));
                    ++ttmp;
                }
            }
        }
        return firstDayTrackModelList;
    }
    private String generatePlan(List<Task> talks, String startTime, String endTime, boolean morning, int indx) {
        StringBuilder track = new StringBuilder();
        String currentTime = startTime;
        if (morning)
            track.append("Track ").append(indx+1).append(":").append(System.getProperty("line.separator"));
        for (Task talk : talks) {
            if (currentTime.compareTo(endTime) > 0) {
                break;
            }
            track.append(currentTime).append(" ").append(talk.getTitle()).append(" ").append(talk.getDuration()).append("min").append(System.getProperty("line.separator"));
            currentTime = incrementTime(currentTime, talk.getDuration());
        }
        track.append(endTime).append(morning ? " Lunch" : " Networking Event").append(morning ? System.getProperty("line.separator") : System.getProperty("line.separator")+System.getProperty("line.separator"));
        return track.toString();
    }
    private String incrementTime(String currentTime, int duration) {
        int hours = Integer.parseInt(currentTime.substring(0, 2));
        int minutes = Integer.parseInt(currentTime.substring(3, 5));

        int newMinutes = (minutes + duration) % 60;
        int newHours = hours + (minutes + duration) / 60;

        return String.format("%02d:%02d%s", newHours, newMinutes, (newHours < 12) ? "AM" : "PM");
    }
}
