package com.n11.spring.vaka.service;

import com.n11.spring.vaka.model.requestModels.UploadTasksRequestModel;
import com.n11.spring.vaka.util.PlanMaker;
import com.n11.spring.vaka.util.TaskParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlannerService {
    @Autowired
    TaskParser taskParser;
    @Autowired
    PlanMaker planMaker;
    public void uploadTask(UploadTasksRequestModel req) {
        if (taskParser.parser(req.getTask()))
            planMaker.plan();
    }
}
