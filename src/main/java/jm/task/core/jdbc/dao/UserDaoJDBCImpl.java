package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() throws SQLException, ClassNotFoundException {}
    public void createUsersTable() {

        String createTable = "CREATE TABLE IF NOT EXISTS users("
                + "id BIGINT NOT NULL AUTO_INCREMENT, "
                + "name VARCHAR(255) NOT NULL, "
                + "lastName VARCHAR(255) NOT NULL, "
                + "age INT NOT NULL, "
                + "PRIMARY KEY (id)"
                + ")";

        try (var statement = connection.createStatement()) {
            //на созданном стэйтменте вызываем запрос
            statement.execute(createTable);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void dropUsersTable() {

        String dropTable = "DROP TABLE IF EXISTS users;";

        try (var statement = connection.createStatement()) {
                //на созданном стэйтменте вызываем запрос
            statement.executeUpdate(dropTable);
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void saveUser(String name, String lastName, byte age) {

        String insertIntoTable = "INSERT INTO users VALUES ('name', 'lastName', age)";

        try (var statement = connection.createStatement()) {
            //на созданном стэйтменте вызываем запрос
            statement.executeUpdate(insertIntoTable);
            User user = new User();

            System.out.println("User  с именем- " + user.getName() + "добавлен в базу данных");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) throws ClassNotFoundException, SQLException {

        String insertIntoTable = "DELETE FROM users WHERE 'id' = id;";

        try (var statement = connection.createStatement()) {
            //на созданном стэйтменте вызываем запрос
            statement.executeUpdate(insertIntoTable);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<User> getAllUsers() throws SQLException {

        String allUsers = "SELECT * FROM users;";

        ResultSet resultSet;
        try (var statement = connection.createStatement()) {
            //на созданном стэйтменте вызываем запрос
            resultSet = statement.executeQuery(allUsers);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //создаю лист юзеров
        List<User> userList = new ArrayList<>();
        //прохожу по всем ячейкам
        while (resultSet.next()) {
            //создаю юзера и присваиваю инфу, что получили через резалтсет
            User user = new User();
            user.setId(resultSet.getLong("id"));
            user.setName(resultSet.getString("name"));
            user.setLastName(resultSet.getString("lastName"));
            user.setAge(resultSet.getByte("age"));
            userList.add(user);
        }

        return userList;
    }

    public void cleanUsersTable() {

        String cleanUsers = "TRUNCATE TABLE users;";

        try (var statement = connection.createStatement()) {
            //на созданном стэйтменте вызываем запрос
            statement.executeUpdate(cleanUsers);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}

