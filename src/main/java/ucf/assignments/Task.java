/*
 * UCF COP3330 Fall 2021 Assignment 4 Solution
 * Copyright 2021 Tyler Snowdon
 */

package ucf.assignments;

import ucf.assignments.utils.DateUtils;

import java.util.Date;
import java.util.UUID;

public class Task {

    public static final int MAX_DESC_LENGTH = 256;

    private String description;
    private Date dueDate;
    private StatusType status;
    private final UUID uuid;

    public Task(String description, Date dueDate) {
        this.description = description.trim();
        this.dueDate = dueDate;
        this.uuid = UUID.randomUUID();
    }

    public Date getDueDate() {
        return new Date();
    }

    public String getDescription() {
        return description;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    public boolean matches(Task task) {
        return task.getId().equals(uuid);
    }

    public String getFormatted() {
        return DateUtils.format(dueDate) + " - " + description;
    }

    @Override
    public String toString() {
        return description;
    }

    public UUID getId() {
        return uuid;
    }
}
