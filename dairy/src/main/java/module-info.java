module com.test.diary {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.test.diary to javafx.fxml;
    exports com.test.diary;
}
