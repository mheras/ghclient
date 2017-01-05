package com.ghclient.app.model.entity;

import java.io.Serializable;

public class Event implements Serializable {

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
