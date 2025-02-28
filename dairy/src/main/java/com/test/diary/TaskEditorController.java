package com.test.diary;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class TaskEditorController {

    @FXML
    private TextField titleField;

    @FXML
    private TextArea descriptionField;

    @FXML
    private DatePicker dueDatePicker;

    @FXML
    private Button saveButton;

    private final DBAdapter dbAdapter = new DBAdapter();

    @FXML
    public void initialize() {
        saveButton.setOnAction(event -> saveTask());
    }

    private void saveTask() {
        String title = titleField.getText();
        String description = descriptionField.getText();
        String dueDate = dueDatePicker.getValue().toString();

        if (title.isEmpty() || dueDate.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Заполните все поля!");
            alert.show();
            return;
        }

        dbAdapter.addTask(title, description, dueDate);

        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
    }
}

