/*
 * UCF COP3330 Fall 2021 Assignment 4 Solution
 * Copyright 2021 Tyler Snowdon
 */

package ucf.assignments;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum StatusType {
    ALL("All", null),
    COMPLETE("Complete", true),
    NOT_COMPLETE("Incomplete", false);

    private final String displayName;
    private final Boolean displayValue;

    StatusType(String displayName, Boolean displayValue) {
        this.displayName = displayName;
        this.displayValue = displayValue;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static List<String> getDisplayNames() {
        return Arrays.stream(values()).map(StatusType::getDisplayName).collect(Collectors.toList());
    }

    public static StatusType getType(String displayName) {
        for (StatusType type : values()) {
            if (type.getDisplayName().equalsIgnoreCase(displayName)) {
                return type;
            }
        }
        return null;
    }

    public boolean getDisplayValue() {
        return displayValue;
    }

    public boolean matches(StatusType type) {
        return this == type || type == ALL;
    }
}
