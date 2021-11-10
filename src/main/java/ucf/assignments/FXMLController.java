/*
 * UCF COP3330 Fall 2021 Assignment 4 Solution
 * Copyright 2021 Tyler Snowdon
 */

package ucf.assignments;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import ucf.assignments.utils.DateUtils;
import ucf.assignments.utils.Logger;

import java.net.URL;
import java.util.*;

public class FXMLController implements Initializable {

    public Button importListButton;
    public Button exportListButton;
    public Button clearListButton;
    public Button newListButton;
    public Button newTaskButton;

    public Button deleteTaskButton;
    public Button updateTaskButton;

    public TextField newListTextField;
    public TextField newTaskDescriptionTextField;
    public TextField newTaskDateField;

    public ComboBox<String> selectListComboBox;
    public ComboBox<String> selectViewComboBox;

    public ListView<String> taskListView;
    @FXML
    private Label label;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /**
         * PSEUDO CODE
         *
         * LOAD TASK LISTS
         * Update the list of possible TaskLists that can be selected
         * Use the String of names to differentiate them
         *
         *
         * LOAD VIEW LISTS
         * Update the list of possible Views that can be selected
         * Use the String of displayNames from the StatusType
         */
    }

    public void render() {
        clearTextFields();
        displayCurrentTaskList();
    }

    public void clearTextFields() {
        newTaskDateField.clear();
        newTaskDescriptionTextField.clear();
    }

    public void displayCurrentTaskList() {
        TaskList curr = App.getCurrentTaskList();
        displayTasks(curr);
    }

    public void displayTasks(TaskList list) {
        taskListView.getItems().clear();
        Map<String, ObservableValue<Boolean>> map = new HashMap<>();
        for (Task task : list.getTasks().values()) {
            map.put(task.getFormatted(), new SimpleBooleanProperty(false));
        }

        // map.put("Task1", new SimpleBooleanProperty(true));
        // map.put("Task2", new SimpleBooleanProperty(true));
        // map.put("Task3", new SimpleBooleanProperty(true));

        taskListView.setEditable(false);
        taskListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        taskListView.getItems().addAll(map.keySet());

        Callback<String, ObservableValue<Boolean>> callback = map::get;
        taskListView.setCellFactory(lv ->
                new CheckBoxListCell<>(callback));
    }

    public void importList(ActionEvent actionEvent) {
        /**
         *  Open File Explorer Prompt for selecting the File
         *      If the File is a valid JSON
         *          Use GSON to deserialize it into a TaskList
         *      If not
         *          Do nothing
         */
    }

    public void exportList(ActionEvent actionEvent) {
        /**
         *  Take the currently selected TaskList in the App
         *      If a JSON doesn't exists with the name of the TaskList
         *          run the TaskList through a GSON serializer
         *      If not
         *          tell the user to rename the file
         */
    }

    public void newList(ActionEvent actionEvent) {
        String newListName = newListTextField.getText();

        if (newListName.isEmpty() || newListName.isBlank()) {
            Logger.debug("No input detected.");
            return;
        }

        if (App.getCurrentTaskList().getName().equals(newListName)) {
            Logger.debug("The List %s already exists", newListName);
            return;
        }

        TaskList newList = new TaskList(newListName);
        App.setCurrentTaskList(newList);
        Logger.debug("List %s created", newListName);
        render();
    }

    public void selectView(ActionEvent actionEvent) {
        /**
         *      Take the tasks from the currentTaskList and remove that ones
         *      that don't meet the conditions of the Status Type
         *      If the view is ALL
         *          Show all
         *      If the view
         *
         */
    }

    public void newTask(ActionEvent actionEvent) {
        String desc = newTaskDescriptionTextField.getText();
        String date = newTaskDateField.getText();

        if (desc.isEmpty()) {
            return;
        }

        if (desc.length() > Task.MAX_DESC_LENGTH) {
            Logger.debug("MAX DESC LENGTH HAS BEEN REACHED");
            return;
        }

        if (App.getCurrentTaskList().containsTask(desc)) {
            return;
        }

        Date taskDate = DateUtils.parse(date);
        Task newTask = new Task(desc, taskDate);
        App.getCurrentTaskList().addTask(newTask);
        App.setCurrentTask(newTask);
        render();
    }

    public void changeList(ActionEvent actionEvent) {
        TaskList curr = App.getCurrentTaskList();
        if (curr != null) {
            App.getCurrentTaskList().setName(newListTextField.getText());
            Logger.debug("%s has been renamed to %s", curr.getName(), newListTextField.getText());
        } else {
            Logger.debug("Current list doesn't exist");
        }
        render();
    }

    public void deleteTask(ActionEvent actionEvent) {
        if (App.getCurrentTask() != null) {
            App.getCurrentTaskList().removeTask(App.getCurrentTask());
            App.clearCurrentTask();
            render();
        }
    }

    public void updateTask(ActionEvent actionEvent) {
        String desc = newTaskDescriptionTextField.getText();
        String date = newTaskDateField.getText();

        if (desc.isEmpty() && date.isEmpty()) {
            return;
        }

        Task curr = App.getCurrentTask();
        if (!desc.isEmpty()) {
            curr.setDescription(desc);
        }

        if (!date.isEmpty()) {
            curr.setDueDate(DateUtils.parse(date));
        }

        App.updateCurrentTaskList(curr);

        render();
    }

    public void listViewClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getTarget() != null) {
            if (mouseEvent.getTarget() instanceof CheckBoxListCell) {
                String taskText = ((CheckBoxListCell) mouseEvent.getTarget()).getText();
                if (App.getCurrentTaskList().containsTask(taskText)) {
                    App.setCurrentTask(App.getCurrentTaskList().getTask(taskText));
                }
            }
        }
    }

    public void clearList(ActionEvent actionEvent) {
        App.getCurrentTaskList().clearTasks();
        render();
    }
}
