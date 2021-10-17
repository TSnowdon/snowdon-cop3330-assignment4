/*
 * UCF COP3330 Fall 2021 Assignment 4 Solution
 * Copyright 2021 Tyler Snowdon
 */

package ucf.assignments;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.*;

public class FXMLController implements Initializable {

    public Button importListButton;
    public Button exportListButton;
    public Button newListButton;
    public Button newTaskButton;

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

        /**
         * I'm keeping this here because I wanted to make sure it works when I actually update the list.
         * ArrayList<String> options = new ArrayList<>();
         * selectListComboBox.setItems(FXCollections.observableArrayList(options));
         */

        /**
         *      I had to test out how the actual code works since IDK FXML
         *      but now I know that this works I know how to write the pseudo code
         *        Map<String, ObservableValue<Boolean>> map = new HashMap<>();
         *        map.put("Task1", new SimpleBooleanProperty(true));
         *        map.put("Task2", new SimpleBooleanProperty(true));
         *        map.put("Task3", new SimpleBooleanProperty(true));
         *
         *        taskListView.setEditable(true);
         *        taskListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
         *
         *        taskListView.getItems().addAll(map.keySet());
         *
         *        Callback<String, ObservableValue<Boolean>> callback = map::get;
         *        taskListView.setCellFactory(lv -> new CheckBoxListCell<>(callback));
         */

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
        /**
         *      Scan the value in the text field
         *      If there is no value in the Text Field
         *          Don't do anything
         *      If there is a value in the Text Field
         *          Check that there is no other TaskList with that name
         *          If there is no TaskList with that name
         *              Add a new TaskList
         *          If not then there is nothing
         */
    }

    public void selectList(ActionEvent actionEvent) {
        /**
         *      Load the selected List into the TaskList view (TM)
         *      If for some reason that TaskList doesn't exist
         *          don't do anything
         *      If it does exist
         *          change the TaskList view (TM)
         */
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
}