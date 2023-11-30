package org.example.database;

import org.example.services.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryService {
    public List<MaxProjectCountClient> findMaxProjectsClient() {
        List<MaxProjectCountClient> result = new ArrayList<>();

        try (Connection connection = Database.getInstance().getConnection()) {
            String sqlContent = new String(Files.readAllBytes(Paths.get("src/main/resources/find_max_projects_client.sql")));
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlContent)) {
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    result.add(new MaxProjectCountClient(resultSet.getString("name"),
                            resultSet.getInt("project_count")));
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<MaxSalaryWorker> findMaxSalaryWorker() {
        List<MaxSalaryWorker> result = new ArrayList<>();

        try (Connection connection = Database.getInstance().getConnection()) {
            String sqlContent = new String(Files.readAllBytes(Paths.get("src/main/resources/find_max_salary_worker.sql")));
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlContent)) {
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    MaxSalaryWorker worker = new MaxSalaryWorker();
                    worker.setName(resultSet.getString("name"));
                    worker.setSalary(resultSet.getDouble("salary"));
                    result.add(worker);
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<LongestProject> findLongestProject() {
        List<LongestProject> result = new ArrayList<>();

        try (Connection connection = Database.getInstance().getConnection()) {
            String sqlContent = new String(Files.readAllBytes(Paths.get("src/main/resources/find_longest_project.sql")));
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlContent)) {
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    LongestProject project = new LongestProject();
                    project.setName(resultSet.getString("name"));
                    project.setDuration(resultSet.getInt("duration"));
                    result.add(project);
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<YoungestEldestWorkers> findYoungestEldestWorkers() {
        List<YoungestEldestWorkers> result = new ArrayList<>();

        try (Connection connection = Database.getInstance().getConnection()) {
            String sqlContent = new String(Files.readAllBytes(Paths.get("src/main/resources/find_youngest_eldest_workers.sql")));
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlContent)) {
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    YoungestEldestWorkers workers = new YoungestEldestWorkers();
                    workers.setYoungestName(resultSet.getString("youngest_name"));
                    workers.setYoungestAge(resultSet.getInt("youngest_age"));
                    workers.setEldestName(resultSet.getString("eldest_name"));
                    workers.setEldestAge(resultSet.getInt("eldest_age"));
                    result.add(workers);
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<ProjectPrice> findProjectPrice() {
        List<ProjectPrice> result = new ArrayList<>();

        try (Connection connection = Database.getInstance().getConnection()) {
            String sqlContent = new String(Files.readAllBytes(Paths.get("src/main/resources/find_project_price.sql")));
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlContent)) {
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    ProjectPrice projectPrice = new ProjectPrice();
                    projectPrice.setProjectName(resultSet.getString("project_name"));
                    projectPrice.setPrice(resultSet.getDouble("price"));
                    result.add(projectPrice);
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        return result;
    }

}