/*
 * UCF COP3330 Fall 2021 Assignment 4 Solution
 * Copyright 2021 Tyler Snowdon
 */

package ucf.assignments;

import com.sun.javafx.collections.ImmutableObservableList;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.util.Callback;
import ucf.assignments.utils.DateParser;
import ucf.assignments.utils.Logger;

import java.net.URL;
import java.util.*;

public class FXMLController implements Initializable {

    public Button importListButton;
    public Button exportListButton;
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
        for (Task task : list.getTasks()) {
            map.put(task.toString(), new SimpleBooleanProperty(false));
        }

        // map.put("Task1", new SimpleBooleanProperty(true));
        // map.put("Task2", new SimpleBooleanProperty(true));
        // map.put("Task3", new SimpleBooleanProperty(true));

        taskListView.setEditable(true);
        taskListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        taskListView.getItems().addAll(map.keySet());

        Callback<String, ObservableValue<Boolean>> callback = map::get;
        taskListView.setCellFactory(lv -> new CheckBoxListCell<>(callback));
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

    public void addTask(ActionEvent actionEvent) {
        /**
         *  If the values in the Text Field can construct a Task
         *      create a Task using those values
         *      insert that Task into the current TaskList in the App
         *  If not
         *      tell the user that they are missing some values
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


        if (App.getCurrentTaskList().containsTask(desc)) {
            return;
        }

        Date taskDate = DateParser.parse(date);
        Task newTask = new Task(desc, taskDate);
        App.getCurrentTaskList().addTask(newTask);
        render();
        /**
         *      Take the values from the two Text Fields
         *      If either one is empty
         *          Tell the user which field is empty
         *          And what they need to put into it
         *          Don't do anything
         *      If not
         *          Check Ff there is no task with the duplicate name (Date is fine)
         *          If there is no duplicate name
         *              Add the task to the currentTaskList
         *          If there is a duplicate
         *              Tell the user that there is a duplicate
         *              Don't do anything
         */
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
            render();
        }
        /**
         * Delete the currently selected task
         * From the currently selected tasklist
         */
    }

    public void updateTask(ActionEvent actionEvent) {
        /**
         * Update the currently selected task
         * From the currently selected tasklist
         *
         * Take into account both description value and date value
         */
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
}
