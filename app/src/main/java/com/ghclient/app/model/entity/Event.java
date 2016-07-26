package com.ghclient.app.model.entity;

public class Event {

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Event{" +
                "type='" + type + '\'' +
                '}';
    }
}
