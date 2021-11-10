/*
 * UCF COP3330 Fall 2021 Assignment 4 Solution
 * Copyright 2021 Tyler Snowdon
 */

package ucf.assignments.utils;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class DateParser {

    private static DateParser instance;

    public DateParser() {

    }

    /**
     * Parses from Strings to Dates
     * Returns todays date if date is empty
     * @param date The input string, must be formatted as "YYYY-MM-DD".
     * @return A new Date that matches the date described in "date".
     */
    public static Date parse(String date) {
        Date out = new Date();
        if (date.isEmpty()) {
            Logger.debug("Empty date found");
            return out;
        }

        int year = 1;
        int month = 1;
        int day = 1;

        date = date.replace("-", " ");
        Scanner reader = new Scanner(date);
        year = reader.nextInt();
        month = reader.nextInt() - 1;
        day = reader.nextInt();
        Logger.debug("Parsed %d %d %d", year, month, day);
        out = new GregorianCalendar(year, month, day).getTime();

        return out;
    }

    public static void initialize() {
        instance = new DateParser();
    }
}
