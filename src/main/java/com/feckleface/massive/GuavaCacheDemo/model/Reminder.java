package com.feckleface.massive.GuavaCacheDemo.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Reminder {

    private String name;
    private String description;
    private String creator;

    public Reminder(String name, String description, String creator) {
        this.name = name;
        this.description = description;
        this.creator = creator;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
}
