package com.test.diary;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        System.out.println(getClass().getResource("/com/test/diary/main-view.fxml")); // Отладочный вывод
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/test/diary/main-view.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Электронный ежедневник");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
