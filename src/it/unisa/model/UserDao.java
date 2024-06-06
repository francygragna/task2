package it.unisa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private Connection getConnection() throws SQLException {
        // Implement your connection logic here
        // Example:
        // return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public void saveUser(UserBean user) throws SQLException {
        String query = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();
        }
    }

    public UserBean doRetrieveByUsername(String username) throws SQLException {
        String query = "SELECT * FROM users WHERE username = ?";
        UserBean user = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    user = new UserBean();
                    user.setUsername(resultSet.getString("username"));
                    user.setPassword(resultSet.getString("password"));
                }
            }
        }
        return user;
    }
}
