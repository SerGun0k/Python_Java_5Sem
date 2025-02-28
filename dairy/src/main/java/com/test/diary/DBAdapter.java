package com.test.diary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBAdapter {
    private static final String DB_URL = "jdbc:sqlite:diary.sqlite";

    private Connection connect() {
        try {
            return DriverManager.getConnection(DB_URL);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка подключения к базе данных", e);
        }
    }

    public void initDatabase() {
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            String createTasksTable = """
                CREATE TABLE IF NOT EXISTS tasks (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    title TEXT NOT NULL,
                    description TEXT,
                    due_date TEXT
                );
            """;
            stmt.execute(createTasksTable);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка инициализации базы данных", e);
        }
    }

    public ResultSet getTasks() {
        String query = "SELECT * FROM tasks";
        try {
            Connection conn = connect();
            return conn.createStatement().executeQuery(query);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка получения задач", e);
        }
    }

    public void addTask(String title, String description, String dueDate) {
        String query = "INSERT INTO tasks (title, description, due_date) VALUES (?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, title);
            stmt.setString(2, description);
            stmt.setString(3, dueDate);
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка добавления задачи", e);
        }
    }

    public void deleteTask(String title) {
        String query = "DELETE FROM tasks WHERE title = ?";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, title);
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка удаления задачи", e);
        }
    }

}
