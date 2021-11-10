/*
 * UCF COP3330 Fall 2021 Assignment 4 Solution
 * Copyright 2021 Tyler Snowdon
 */

package ucf.assignments;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ucf.assignments.utils.DateParser;
import ucf.assignments.utils.Logger;

import java.util.ArrayList;

public class App extends Application {

    private static TaskList currentList = null;
    private static Task currentTask = null;
    private static StatusType currentView = null;

    @Override
    // Called before FXMLController
    public void start(Stage stage) throws Exception {
        DateParser.initialize();
        Logger.initialize();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("ucf/assignments/scene.fxml"));
        currentList = new TaskList("List 1");
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        stage.setTitle("Tasklist GUI Test");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static TaskList getCurrentTaskList() {
        return currentList;
    }

    public static Task getCurrentTask() {
        return currentTask;
    }

    public static void setCurrentTaskList(TaskList newList) {
        currentList = newList;
        Logger.debug("%s is set to the current list", newList.getName());
    }

    public static void setCurrentTask(Task target) {
        currentTask = target;
        Logger.debug("\"%s\" is set to the current task", target.getDescription());
    }
}
