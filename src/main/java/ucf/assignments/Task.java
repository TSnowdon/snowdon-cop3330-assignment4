/*
 * UCF COP3330 Fall 2021 Assignment 4 Solution
 * Copyright 2021 Tyler Snowdon
 */

package ucf.assignments;

import java.util.Date;

public class Task {

    private String description;
    private Date dueDate;
    private StatusType status;

    public Task(String description, Date dueDate) {
        this.description = description;
        this.dueDate = dueDate;
    }

    public Date getDueDate() {
        return new Date();
    }

    public String getDescription() {
        return "description";
    }

    public void setDueDate(Date dueDate) {
        /**
         * Set this.dueDate to dueDate
         */
    }

    public void setDescription(String description) {
        /**
         * Set this.description to description
         */
    }

    public void setStatus(StatusType status){
        /**
         * Set this.status to status
         */
    }
}
