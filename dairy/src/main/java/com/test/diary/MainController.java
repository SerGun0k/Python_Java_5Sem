package com.test.diary;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.ResultSet;

public class MainController {

    @FXML
    private TableView<Task> taskTable;

    @FXML
    private TableColumn<Task, String> titleColumn;

    @FXML
    private TableColumn<Task, String> descriptionColumn;

    @FXML
    private TableColumn<Task, String> dueDateColumn;

    @FXML
    private Button addTaskButton;

    @FXML
    private Button deleteSelectedTask;

    private final ObservableList<Task> taskList = FXCollections.observableArrayList();
    private final DBAdapter dbAdapter = new DBAdapter();

    @FXML
    public void initialize() {
        dbAdapter.initDatabase();

        titleColumn.setCellValueFactory(data -> data.getValue().titleProperty());
        descriptionColumn.setCellValueFactory(data -> data.getValue().descriptionProperty());
        dueDateColumn.setCellValueFactory(data -> data.getValue().dueDateProperty());

        loadTasks();

        addTaskButton.setOnAction(event -> openTaskEditor());
        deleteSelectedTask.setOnAction(event -> deleteSelectedTask());
    }

    private void deleteSelectedTask() {
        Task selectedTask = taskTable.getSelectionModel().getSelectedItem();
        if (selectedTask != null) {
            dbAdapter.deleteTask(selectedTask.titleProperty().get());
            taskList.remove(selectedTask);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Выберите задачу для удаления!");
            alert.show();
        }
    }


    private void loadTasks() {
        taskList.clear();
        try {
            ResultSet rs = dbAdapter.getTasks();
            while (rs.next()) {
                taskList.add(new Task(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("due_date")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        taskTable.setItems(taskList);
    }

    private void openTaskEditor() {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/test/diary/task-editor.fxml"));
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.setTitle("Добавить задачу");
            stage.showAndWait();
            loadTasks();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
