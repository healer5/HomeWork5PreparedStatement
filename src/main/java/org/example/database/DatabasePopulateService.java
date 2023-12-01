package org.example.database;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.example.database.DatabaseQueryService.SQL_PATH;

public class DatabasePopulateService {
    public static void main(String[] args) {
        executeSqlFile("populate_db.sql");
        System.out.println("Database populated successfully.");
    }

    private static void executeSqlFile(String fileName) {
        try (Connection connection = Database.getInstance().getConnection();
             Statement statement = connection.createStatement()) {

            String sqlContent = new String(Files.readAllBytes(Paths.get(SQL_PATH + fileName)));
            statement.execute(sqlContent);

        } catch (SQLException | IOException e) {
            throw new RuntimeException("Error executing SQL file: " + fileName, e);
        }
    }
}
