package com.n11.spring.vaka.controller;

import com.n11.spring.vaka.model.requestModels.UploadTasksRequestModel;
import com.n11.spring.vaka.model.responseModels.BaseResponseModel;
import com.n11.spring.vaka.service.PlannerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/")
public class Planner {
    @Autowired
    PlannerService plannerService;
    @PostMapping("uploadTasks")
    public BaseResponseModel uploadTasks(@Valid @RequestBody UploadTasksRequestModel request) {
        BaseResponseModel res = new BaseResponseModel();
        plannerService.uploadTask(request);
        res.setStatus("OK");
        return res;
    }
}
