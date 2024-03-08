package repository;

import loadingproperties.PropertiesLoader;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserRepository {
    /*public static List<User> userData(){
        List<User> users = new ArrayList<>();
        return users;
    }*/

    public static List<User> getAllUsers() {
        String sql = "SELECT * FROM users";
        List<User> userList = new ArrayList<>();
        PropertiesLoader.LoadPropertiesFile();
        try (
                Connection connection = DriverManager.getConnection(
                        /*PropertiesLoader.properties.getProperty("database_url"),
                        PropertiesLoader.properties.getProperty("database_username"),
                        PropertiesLoader.properties.getProperty("database_password")*/
                        "jdbc:postgresql://localhost:5432/postgres",
                        "postgres",
                        "789sokny"
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
}
