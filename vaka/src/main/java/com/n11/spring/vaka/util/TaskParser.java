package com.n11.spring.vaka.util;

import com.n11.spring.vaka.cache.Konferans;
import com.n11.spring.vaka.model.Task;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class TaskParser {
    public boolean parser(String s) {
        String[] splt = s.split("\n");
        Pattern pattern;
        Matcher matcher;
        for(String sp: splt) {
            pattern = Pattern.compile(" (.\\d*?)min$");
            matcher = pattern.matcher(sp);
            try {
                if (matcher.find()) {
                    Konferans.getInstance().tumSunumEkle(new Task(sp.replaceAll(" (.\\d*?)min$", ""), Integer.parseInt(matcher.group(1))));
                } else if (s.contains("lightning")) {
                    Konferans.getInstance().tumSunumEkle(new Task(sp.replaceAll(" lightning", ""), 5));
                }
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }
}
