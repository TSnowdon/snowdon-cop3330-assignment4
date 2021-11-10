/*
 * UCF COP3330 Fall 2021 Assignment 4 Solution
 * Copyright 2021 Tyler Snowdon
 */

package ucf.assignments;

import java.util.ArrayList;

public class TaskList {

    private String name;
    private ArrayList<Task> tasks;

    public TaskList(String name) {
        this.name = name;
        this.tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean containsTask(String desc) {
        return getTask(desc) != null;
    }

    public Task getTask(String desc) {
        for (Task task : tasks) {
            if (task.getDescription().equalsIgnoreCase(desc) || task.toString().equalsIgnoreCase(desc)) {
                return task;
            }
        }
        return null;
    }

    public void updateTasks() {
        /**
         * For each Task in tasks
         * check if there dueDate has passed
         * if there dueDate has passed update there status
         */
    }
}
