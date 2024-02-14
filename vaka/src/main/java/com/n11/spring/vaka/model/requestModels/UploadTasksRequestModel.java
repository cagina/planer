package com.n11.spring.vaka.model.requestModels;

import com.n11.spring.vaka.validator.Task;

public class UploadTasksRequestModel {
    @Task
    private String task;

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }
}
