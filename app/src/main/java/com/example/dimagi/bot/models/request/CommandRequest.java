package com.example.dimagi.bot.models.request;

import com.example.dimagi.bot.rest.JsonMapper;

import java.io.Serializable;
import java.util.List;

public class CommandRequest extends JsonMapper implements Serializable {

    private String command;
    private List<String> args;

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public List<String> getArgs() {
        return args;
    }

    public void setArgs(List<String> args) {
        this.args = args;
    }
}
