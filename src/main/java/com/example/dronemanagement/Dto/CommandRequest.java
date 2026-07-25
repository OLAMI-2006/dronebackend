package com.example.dronemanagement.Dto;

public class CommandRequest {
    private String command;
    private Long droneId;

    public CommandRequest() {
    }

    public CommandRequest(String command, Long droneId) {
        this.command = command;
        this.droneId = droneId;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Long getDroneId() {
        return droneId;
    }

    public void setDroneId(Long droneId) {
        this.droneId = droneId;
    }
}