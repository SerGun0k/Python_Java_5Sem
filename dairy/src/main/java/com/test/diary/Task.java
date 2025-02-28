package com.test.diary;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Task {
    private final StringProperty title;
    private final StringProperty description;
    private final StringProperty dueDate;

    public Task(int id, String title, String description, String dueDate) {
        this.title = new SimpleStringProperty(title);
        this.description = new SimpleStringProperty(description);
        this.dueDate = new SimpleStringProperty(dueDate);
    }

    public StringProperty titleProperty() {
        return title;
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public StringProperty dueDateProperty() {
        return dueDate;
    }
}
