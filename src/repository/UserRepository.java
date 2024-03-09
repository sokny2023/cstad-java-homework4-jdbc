package repository;

import loadingproperties.PropertiesLoader;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    public static void connectionDriver(){

    }

    public static List<User> getAllUsers() {
        String sql = "SELECT * FROM users";
        List<User> userList = new ArrayList<>();
        PropertiesLoader.LoadPropertiesFile();
        try (
                Connection connection = DriverManager.getConnection(
                        PropertiesLoader.properties.getProperty("database_url"),
                        PropertiesLoader.properties.getProperty("database_username"),
                        PropertiesLoader.properties.getProperty("database_password")
                );
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
        ) {
            while (resultSet.next()){
                userList.add(
                        new User(
                                resultSet.getInt("user_id"),
                                resultSet.getString("user_uuid"),
                                resultSet.getString("user_name"),
                                resultSet.getString("user_email"),
                                resultSet.getString("user_password"),
                                resultSet.getBoolean("is_deleted"),
                                resultSet.getBoolean("is_verified")
                        )

                );
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return userList;
    }

    /*public static User createUser(User user){
        String sql = """
        INSERT INTO usrs (user_id, user_uuid, user_name, user_email, user_password, is_deleted, is_verified)
        VALUES (?, ?, ?, ?, ?, ?, ?);
        """;
        List<User> createUser = new ArrayList<>();
        PropertiesLoader.LoadPropertiesFile();
        try (
                Connection connection = DriverManager.getConnection(
                        PropertiesLoader.properties.getProperty("database_url"),
                        PropertiesLoader.properties.getProperty("database_username"),
                        PropertiesLoader.properties.getProperty("database_password")
                );
                //Statement  = connection.createStatement();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            System.out.println("Hello");
            preparedStatement.setObject(1, user.getUserId());
            preparedStatement.setObject(2, user.getUserUuid());
            preparedStatement.setObject(3, user.getUserName());
            preparedStatement.setObject(4, user.getUserEmail());
            preparedStatement.setObject(5, user.getUserPassword());
            preparedStatement.setObject(6, user.getIsDeleted());
            preparedStatement.setObject(7, user.getIsVerified());
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return user;
    }*/

    public static User createUser(User user){
        String sql = """
    INSERT INTO users (user_id, user_uuid, user_name, user_email, user_password, is_deleted, is_verified)
    VALUES (?, ?, ?, ?, ?, ?, ?);
    """;
        PropertiesLoader.LoadPropertiesFile();
        try (
                Connection connection = DriverManager.getConnection(
                        PropertiesLoader.properties.getProperty("database_url"),
                        PropertiesLoader.properties.getProperty("database_username"),
                        PropertiesLoader.properties.getProperty("database_password")
                );
                PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            preparedStatement.setInt(1, user.getUserId());
            preparedStatement.setString(2, user.getUserUuid());
            preparedStatement.setString(3, user.getUserName());
            preparedStatement.setString(4, user.getUserEmail());
            preparedStatement.setString(5, user.getUserPassword());
            preparedStatement.setBoolean(6, user.getIsDeleted());
            preparedStatement.setBoolean(7, user.getIsVerified());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setUserId(generatedKeys.getInt(1)); // This assumes your User object has a setUserId method.
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
            // Depending on your error handling, you might want to re-throw, return null, or handle this differently
            return null;
        }
        return user;
    }


}
