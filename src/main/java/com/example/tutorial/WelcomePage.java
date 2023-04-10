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

public class WelcomePage {
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
    public void taskBtn(ActionEvent actionEvent) {
        makeDialog();
    }
    public void makeDialog(){
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
        Label dateLabel = new Label("for when?");
        DatePicker datePicker = new DatePicker();

        //make any other gui stuff you want to add.

        //add all the labels and text fields etc...
        dialogPane.setContent(new VBox(taskLabel, task, detailsLabel, detail, dateLabel, datePicker));
        //make an ok button
        final Button btOk = (Button) dialog.getDialogPane().lookupButton(ButtonType.OK);
        //Create what you want it to do when you click the button
        btOk.addEventFilter(
                ActionEvent.ACTION,
                event -> {
                    if( !detail.getText().equals("") && !task.getText().equals("")&& datePicker.getValue() != null) { // if all your fields and things ARENT EMPTY
                        LocalDate date = datePicker.getValue();
                        //read them all text fields and make a new object.
                        //tasks.add(new Task("Name",  "e", date, date, 5));
                        /**
                         try {
                         saveJson(new ActionEvent()); //try to save the json again so it keeps the new course.
                         } catch (IOException e) {
                         e.printStackTrace();
                         }
                         **/
                    } else { //else if some text field is empty or incorrect. give them an error message
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Incorrect input");
                        alert.setHeaderText(null);
                        alert.setContentText("Make sure everything is filled in correctly.");
                        alert.showAndWait();
                        event.consume(); //consume the ok button event so it doesn't close the dialog.
                    }

                });
        Optional<Task> optionalResult = dialog.showAndWait(); //show the dialog.
    }

}