package com.example.dimagi.bot.entities;

public class CommandHistory {

    private int id;
    private int commandId;
    private String commandStatement;
    private int employeeId;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCommandId() {
        return commandId;
    }

    public void setCommandId(int commandId) {
        this.commandId = commandId;
    }

    public String getCommandStatement() {
        return commandStatement;
    }

    public void setCommandStatement(String commandStatement) {
        this.commandStatement = commandStatement;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
}
