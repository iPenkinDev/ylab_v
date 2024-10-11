package ru.ylab.model;

import java.time.LocalDateTime;

public class Habit {
    private String name;
    private String description;
    private String frequency;
    private LocalDateTime createdAt;

    public Habit(String name, String description, String frequency) {
        this.name = name;
        this.description = description;
        this.frequency = frequency;
        this.createdAt = LocalDateTime.now();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getFrequency() {
        return frequency;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }
}