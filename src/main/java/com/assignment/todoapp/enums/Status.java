package com.assignment.todoapp.enums;

/**
 * Created by Mussadiq on 5/8/2018.
 */
public enum Status {
    TODO("TODO"),
    COMPLETED("COMPLETED");

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    Status(String name){
        this.name = name;
    }
}
