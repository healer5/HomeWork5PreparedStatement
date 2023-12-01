package org.example.database;

import org.example.services.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryService {
    public static final String SQL_PATH = "src/main/resources/";

    public List<MaxProjectCountClient> findMaxProjectsClient() {
        String sqlContent = readSqlFile("find_max_projects_client.sql");
        return executeMaxProjectCountClientQuery(sqlContent);
    }

    public List<MaxSalaryWorker> findMaxSalaryWorker() {
        String sqlContent = readSqlFile("find_max_salary_worker.sql");
        return executeMaxSalaryWorkerQuery(sqlContent);
    }

    public List<LongestProject> findLongestProject() {
        String sqlContent = readSqlFile("find_longest_project.sql");
        return executeLongestProjectQuery(sqlContent);
    }

    public List<YoungestEldestWorkers> findYoungestEldestWorkers() {
        String sqlContent = readSqlFile("find_youngest_eldest_workers.sql");
        return executeYoungestEldestWorkersQuery(sqlContent);
    }

    public List<ProjectPrice> findProjectPrice() {
        String sqlContent = readSqlFile("find_project_price.sql");
        return executeProjectPriceQuery(sqlContent);
    }

    private String readSqlFile(String fileName) {
        try {
            return new String(Files.readAllBytes(Paths.get(SQL_PATH + fileName)));
        } catch (IOException e) {
            throw new RuntimeException("Error reading SQL file: " + fileName, e);
        }
    }

    private List<MaxProjectCountClient> executeMaxProjectCountClientQuery(String sql) {
        List<MaxProjectCountClient> result = new ArrayList<>();

        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                result.add(new MaxProjectCountClient(
                        resultSet.getString("name"),
                        resultSet.getInt("project_count")));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error executing MaxProjectCountClient query", e);
        }

        return result;
    }

    private List<MaxSalaryWorker> executeMaxSalaryWorkerQuery(String sql) {
        List<MaxSalaryWorker> result = new ArrayList<>();

        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                MaxSalaryWorker worker = new MaxSalaryWorker();
                worker.setName(resultSet.getString("name"));
                worker.setSalary(resultSet.getDouble("salary"));
                result.add(worker);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error executing MaxSalaryWorker query", e);
        }

        return result;
    }

    private List<LongestProject> executeLongestProjectQuery(String sql) {
        List<LongestProject> result = new ArrayList<>();

        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                LongestProject project = new LongestProject();
                project.setName(resultSet.getString("name"));
                project.setDuration(resultSet.getInt("duration"));
                result.add(project);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error executing LongestProject query", e);
        }

        return result;
    }

    private List<YoungestEldestWorkers> executeYoungestEldestWorkersQuery(String sql) {
        List<YoungestEldestWorkers> result = new ArrayList<>();

        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                YoungestEldestWorkers workers = new YoungestEldestWorkers();
                workers.setYoungestName(resultSet.getString("youngest_name"));
                workers.setYoungestAge(resultSet.getInt("youngest_age"));
                workers.setEldestName(resultSet.getString("eldest_name"));
                workers.setEldestAge(resultSet.getInt("eldest_age"));
                result.add(workers);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error executing YoungestEldestWorkers query", e);
        }

        return result;
    }

    private List<ProjectPrice> executeProjectPriceQuery(String sql) {
        List<ProjectPrice> result = new ArrayList<>();

        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ProjectPrice projectPrice = new ProjectPrice();
                projectPrice.setProjectName(resultSet.getString("project_name"));
                projectPrice.setPrice(resultSet.getDouble("price"));
                result.add(projectPrice);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error executing ProjectPrice query", e);
        }

        return result;
    }
}
