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

import java.util.ArrayList;

public class App extends Application {

    private static ArrayList<TaskList> lists;
    private static TaskList currentList;
    private static StatusType currentView = null;

    @Override
    // Called before FXMLController
    public void start(Stage stage) throws Exception {
        System.out.println("start is called...");
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("ucf/assignments/scene.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        stage.setTitle("Tasklist GUI Test");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void addTaskList(TaskList newList) {
        /**
         * Add newList to tasks
         */
    }
}
