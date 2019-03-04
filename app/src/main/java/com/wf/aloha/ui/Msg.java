package com.wf.aloha.ui;

public class Msg {
    public String name;
    public String type;

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Msg(String name, String type) {
        this.name = name;
        this.type = type;
    }
}
