package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            String SQL = "CREATE  TABLE IF NOT EXISTS users(id INT AUTO_INCREMENT, + " +
                    "name VARCHAR(50), last_name VARCHAR (50), age INT not NULL, PRIMARY KEY (id));";
            ResultSet resultSet = statement.executeQuery(SQL);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            String SQL = "DROP TABLE IF EXISTS users;";
            ResultSet resultSet = statement.executeQuery(SQL);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement prepStat =
                     connection.prepareStatement("INSERT INTO usersTable(name, last_name, age) VALUES (?, ?, ?);")) {
            prepStat.setString(1, name);
            prepStat.setString(2, lastName);
            prepStat.setByte(3, age);

            prepStat.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            String SQL = "SELECT * FROM pp1;";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));

                users.add(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
            String SQL = "DELETE FROM pp1;";
            ResultSet resultSet = statement.executeQuery(SQL);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
