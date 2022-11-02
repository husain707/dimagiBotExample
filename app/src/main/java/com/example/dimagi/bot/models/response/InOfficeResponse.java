package com.example.dimagi.bot.models.response;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.dimagi.bot.entities.Employee;
import com.example.dimagi.bot.enums.TaskTypes;
import com.example.dimagi.bot.models.ChatItem;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class InOfficeResponse extends GeneralResponse implements ChatItem {

    private List<Employee> availableEmployees;

    public InOfficeResponse(String json) throws IOException {
        super(json);
    }

    public InOfficeResponse(String status, List<Employee> availableEmployees) {
        super(status, TaskTypes.display);
        this.availableEmployees = availableEmployees;
    }

    public List<Employee> getAvailableEmployees() {
        return availableEmployees;
    }

    public void setAvailableEmployees(List<Employee> availableEmployees) {
        this.availableEmployees = availableEmployees;
    }

    @Override
    public String getTime() {
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public String getData() {
        if(availableEmployees != null && !availableEmployees.isEmpty()){
            return availableEmployees.stream().map(e -> e.getName()).collect(Collectors.joining()).toString();
        }else return "";
    }
}
