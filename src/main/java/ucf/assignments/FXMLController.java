/*
 * UCF COP3330 Fall 2021 Assignment 4 Solution
 * Copyright 2021 Tyler Snowdon
 */

package ucf.assignments;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
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
    public Button newTaskButton;

    public Button deleteTaskButton;
    public Button updateTaskButton;

    public TextField newListTextField;
    public TextField newTaskDescriptionTextField;
    public TextField newTaskDateField;

    public ComboBox<String> selectViewComboBox;

    public ListView<Task> taskListView;
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
        render();
    }

    public void render() {
        Logger.debug("rendering.");
        clearTextFields();
        displayStatusView();
        displayCurrentTaskList();
    }

    public void displayStatusView() {
        if (selectViewComboBox.getItems().isEmpty()) {
            selectViewComboBox.getItems().addAll(StatusType.getDisplayNames());
        }
    }

    public void clearTextFields() {
        newTaskDateField.clear();
        newTaskDescriptionTextField.clear();
    }

    public void displayCurrentTaskList() {
        TaskList curr = App.getCurrentTaskList();
        if (curr != null) {
            Logger.debug("rendering..");
            displayTasks(curr);
        }
    }

    public void displayTasks(TaskList list) {
        Logger.debug("rendering...");
        taskListView.getItems().clear();
        Map<Task, ObservableValue<Boolean>> map = new HashMap<>();
        StatusType currView = App.getCurrentView();
        Logger.debug("-> Now viewing %s tasks", currView != null ? currView.getDisplayName() : "DEFAULT");
        for (Task task : list.getTasks().values()) {
            if (currView == null || task.getStatus().matches(currView)) {
                map.put(task, new SimpleBooleanProperty(false));
            }
        }

        taskListView.setEditable(false);
        taskListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        taskListView.getItems().addAll(map.keySet());

        taskListView.setCellFactory(CheckBoxListCell.forListView(new Callback<Task, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(Task task) {
                BooleanProperty observable = new SimpleBooleanProperty(task.getStatus().getDisplayValue());
                observable.addListener((obs, before, after) -> {
                    task.setStatus(after ? StatusType.COMPLETE : StatusType.NOT_COMPLETE);
                });
                return observable;
            }
        }));
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
        String viewValue = selectViewComboBox.getValue();
        StatusType viewType = StatusType.getType(viewValue);
        if (viewType != null) {
            App.setCurrentView(viewType);
        }
        render();
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
