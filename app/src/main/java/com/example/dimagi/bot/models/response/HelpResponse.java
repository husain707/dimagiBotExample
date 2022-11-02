package com.example.dimagi.bot.models.response;

import com.example.dimagi.bot.entities.BotCommand;
import com.example.dimagi.bot.enums.TaskTypes;

import java.io.IOException;
import java.util.List;

public class HelpResponse extends GeneralResponse{

    private List<BotCommand> commands;

    public HelpResponse(String json) throws IOException {
        super(json);
    }

    public HelpResponse(String status, List<BotCommand> commands) {
        super(status, TaskTypes.display);
        this.commands = commands;
    }

    public List<BotCommand> getCommands() {
        return commands;
    }

    public void setCommands(List<BotCommand> commands) {
        this.commands = commands;
    }
}
