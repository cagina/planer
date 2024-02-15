package com.n11.spring.vaka.service;

import com.n11.spring.vaka.cache.Konferans;
import com.n11.spring.vaka.model.requestModels.UploadTasksRequestModel;
import com.n11.spring.vaka.model.responseModels.BaseResponseModel;
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
    public BaseResponseModel uploadTask(UploadTasksRequestModel req) {
        BaseResponseModel res = new BaseResponseModel();
        if (taskParser.parser(req.getTask()))
            planMaker.plan();
        res.setResult(Konferans.getInstance().getTrackResultTwoDays().toString());
        res.setAllPossiblePossibilities(Konferans.getInstance().getTrackResult().toString());
        return res;
    }
}
