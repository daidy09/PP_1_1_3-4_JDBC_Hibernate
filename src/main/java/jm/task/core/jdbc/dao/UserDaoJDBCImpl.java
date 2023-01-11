package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() throws SQLException, ClassNotFoundException {}
    public void createUsersTable() throws SQLException {

        String createTable = "CREATE TABLE IF NOT EXISTS users("
                + "id BIGINT NOT NULL AUTO_INCREMENT, "
                + "name VARCHAR(255) NOT NULL, "
                + "lastName VARCHAR(255) NOT NULL, "
                + "age INT NOT NULL, "
                + "PRIMARY KEY (id)"
                + ")";

        try (Statement statement = connection.createStatement()) {
            //на созданном стэйтменте вызываем запрос
            statement.execute(createTable);
        }
    }


    public void dropUsersTable() throws SQLException {

        String dropTable = "DROP TABLE IF EXISTS users";

        try (Statement statement = connection.createStatement()) {
                //на созданном стэйтменте вызываем запрос
            statement.executeUpdate(dropTable);
        }

    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {

 //       String insertIntoTable = "INSERT INTO users(name, lastName, age) VALUES ('name', 'lastName', age)";
        String insertIntoTable = "INSERT INTO users(name, lastName, age) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement=connection.prepareStatement(insertIntoTable)) {
            //на созданном стэйтменте вызываем запрос
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User  с именем- " + name + "добавлен в базу данных");
        }

    }

    public void removeUserById(long id) throws ClassNotFoundException, SQLException {

        String deleteFromTable = "DELETE FROM users WHERE id = ?";

       try (PreparedStatement preparedStatement=connection.prepareStatement(deleteFromTable)) {
            //на созданном стэйтменте вызываем запрос
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        }
    }

    public List<User> getAllUsers() throws SQLException {

        String allUsers = "SELECT id, name, lastName, age FROM users;";

        ResultSet resultSet;
//        try (Statement statement = connection.createStatement()) {
//            //на созданном стэйтменте вызываем запрос
//            resultSet = statement.executeQuery(allUsers);
        try (PreparedStatement preparedStatement = connection.prepareStatement(allUsers)) {
            //на созданном стэйтменте вызываем запрос
            resultSet = preparedStatement.executeQuery();
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
            System.out.println(user);
        }

        return userList;
    }
//
    public void cleanUsersTable() throws SQLException {

        String cleanUsers = "TRUNCATE TABLE users";

        try (Statement statement = connection.createStatement()) {
            //на созданном стэйтменте вызываем запрос
            statement.executeUpdate(cleanUsers);
        }

    }
}

