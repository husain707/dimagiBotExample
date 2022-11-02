package com.example.dimagi.bot.data.command;

import android.content.Context;

import com.example.dimagi.bot.entities.BotCommand;
import com.example.dimagi.bot.models.request.CommandRequest;

import java.util.List;

public class CommandRepository {

    private static volatile CommandRepository instance;

    private CommandDataSource dataSource;

    private List<BotCommand> commands;

    // private constructor : singleton access
    private CommandRepository(CommandDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static CommandRepository getInstance() {
        if (instance == null) {
            instance = new CommandRepository(new CommandDataSource());
        }
        return instance;
    }

    private void setCommands(List<BotCommand> commands) {
        this.commands = commands;
    }

    public void executeCommand(Context context
            , CommandRequest commandRequest, CommandDataSource.CommandExecutionCallback callback) {
        // handle sales fetch operation
        dataSource.executeCommand(context, commandRequest, callback::onResult);
    }
}
