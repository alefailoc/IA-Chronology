package com.example.tutorial;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

public class TimeTableController {
    public ObservableList<Task> tasks = FXCollections.observableArrayList();
    public HBox weekendHBox;
    public HBox weekdayHBox;

    @FXML
    protected void onHelloButtonClick() throws IOException {
        StartApplication.setRoot("Hello-Two-view");
    }

    public void initialize() {
        /**
         * 1. how do I add an item to a table column?
         * 2. how can I edit an item in the column?
         *
         *
         */
        Date currentDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.WEEK_OF_YEAR, 1);
        Date oneWeekLater = cal.getTime();

        tasks.add(new Task("homework", "taskDetails", oneWeekLater, 2));

        //Weekend code, tables
        TableView<Task> saturdayTable = new TableView<Task>();
        TableView<Task> sundayTable = new TableView<Task>();
        saturdayTable.setEditable(true);
        sundayTable.setEditable(true);

        saturdayTable.setPrefWidth(200);
        sundayTable.setPrefWidth(200);

        TableColumn<Task, String> saturdayColumn = new TableColumn<Task, String>("Saturday");
        saturdayColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("task"));
        saturdayColumn.setPrefWidth(200);
        saturdayTable.getColumns().add(saturdayColumn);

        TableColumn<Task, String> sundayColumn = new TableColumn<Task, String>("Sunday");
        sundayColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("task"));
        sundayColumn.setPrefWidth(200);
        sundayTable.getColumns().add(sundayColumn);

        weekendHBox.getChildren().add(saturdayTable);
        weekendHBox.getChildren().add(sundayTable);

        //Weekday code, tables
        TableView<Task> mondayTable = new TableView<Task>();
        TableView<Task> tuesdayTable = new TableView<Task>();
        TableView<Task> wednesdayTable = new TableView<Task>();
        TableView<Task> thursdayTable = new TableView<Task>();
        TableView<Task> fridayTable = new TableView<Task>();
        mondayTable.setEditable(true);
        tuesdayTable.setEditable(true);
        wednesdayTable.setEditable(true);
        thursdayTable.setEditable(true);
        fridayTable.setEditable(true);

        TableColumn<Task, String> mondayColumn = new TableColumn<Task, String>("Monday");
        mondayColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("task"));
        mondayColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        TableColumn<Task, String> tuesdayColumn = new TableColumn<Task, String>("Tuesday");
        tuesdayColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("task"));
        tuesdayColumn.setCellFactory(TextFieldTableCell.forTableColumn());


        TableColumn<Task, String> wednesdayColumn = new TableColumn<Task, String>("Wednesday");
        wednesdayColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("task"));
        wednesdayColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        TableColumn<Task, String> thursdayColumn = new TableColumn<Task, String>("Thursday");
        thursdayColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("task"));
        thursdayColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        TableColumn<Task, String> fridayColumn = new TableColumn<Task, String>("Friday");
        fridayColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("task"));
        fridayColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        mondayTable.getColumns().add(mondayColumn);
        tuesdayTable.getColumns().add(tuesdayColumn);
        wednesdayTable.getColumns().add(wednesdayColumn);
        thursdayTable.getColumns().add(thursdayColumn);
        fridayTable.getColumns().add(fridayColumn);

        mondayTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tuesdayTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        wednesdayTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        thursdayTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        fridayTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        weekdayHBox.getChildren().add(mondayTable);
        weekdayHBox.getChildren().add(tuesdayTable);
        weekdayHBox.getChildren().add(wednesdayTable);
        weekdayHBox.getChildren().add(thursdayTable);
        weekdayHBox.getChildren().add(fridayTable);

    }

    public void switchToPrimary(ActionEvent actionEvent) {
    }

    public void taskBtn(ActionEvent actionEvent) {
        makeDialog();
    }

    public void makeDialog() {
        Dialog<Task> dialog = new Dialog<>();
        dialog.initModality(Modality.NONE);
        Stage stage = (Stage) StartApplication.getScene().getWindow();
        dialog.initOwner(stage);

        //start making the stuff in the dialog
        dialog.setTitle("insert");
        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        Label taskLabel = new Label("task");
        TextField task = new TextField("");
        Label detailsLabel = new Label("task details");
        TextField detail = new TextField("");

        // Add a DatePicker control
        Label dueDateLabel = new Label("due date");
        DatePicker dueDate = new DatePicker();

        // Add a Spinner control
        Label priorityLabel = new Label("priority");
        Spinner<Integer> priority = new Spinner<>(1, 5, 1);

        VBox vbox = new VBox(taskLabel, task, detailsLabel, detail, dueDateLabel, dueDate, priorityLabel, priority);
        dialogPane.setContent(vbox);

        dialog.setResultConverter(button -> {
            if (button == ButtonType.OK) {
                String taskText = task.getText();
                String detailText = detail.getText();
                LocalDate dueDateValue = dueDate.getValue();
                int priorityValue = priority.getValue();

                // Create a new Task object using the retrieved data
                Task newTask = new Task(taskText, detailText, dueDateValue, priorityValue);

                // Add the new Task object to the tasks list
                tasks.add(newTask);
            }

            return null;
        });

        Optional<Task> result = dialog.showAndWait();
    }
}