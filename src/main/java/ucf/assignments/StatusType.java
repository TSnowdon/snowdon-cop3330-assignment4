/*
 * UCF COP3330 Fall 2021 Assignment 4 Solution
 * Copyright 2021 Tyler Snowdon
 */

package ucf.assignments;

public enum StatusType {
    COMPLETE("Done"),
    NOT_COMPLETE("Not Done");

    private final String displayName;

    StatusType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return "displayName";
    }
}
