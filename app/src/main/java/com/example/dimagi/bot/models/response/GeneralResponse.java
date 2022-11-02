package com.example.dimagi.bot.models.response;

import com.example.dimagi.bot.enums.TaskTypes;
import com.example.dimagi.bot.rest.JsonMapper;

import java.io.IOException;
import java.io.Serializable;

public class GeneralResponse extends JsonMapper implements Serializable {

    private String status;
    private TaskTypes taskType;

    public GeneralResponse(String json) throws IOException {
        fromJson(json);
    }

    public GeneralResponse(String status, TaskTypes taskType) {
        this.status = status;
        this.taskType = taskType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public TaskTypes getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskTypes taskType) {
        this.taskType = taskType;
    }
}
