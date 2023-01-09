package jm.task.core.jdbc.dao;

import com.mysql.cj.Session;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.math.BigInteger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Util util = new Util(); //нам надо будет соединение, создаем его для всех методов
    Connection connection = util.getConnection();
    Statement statement = connection.createStatement();

    public UserDaoJDBCImpl() throws SQLException, ClassNotFoundException {

    }

    public void createUsersTable() throws ClassNotFoundException, SQLException {

        String createTable = "CREATE TABLE IF NOT EXISTS users("
                + "id BIGINT NOT NULL AUTO_INCREMENT, "
                + "name VARCHAR(255) NOT NULL, "
                + "lastName VARCHAR(255) NOT NULL, "
                + "age INT NOT NULL, "
                + "PRIMARY KEY (id)"
                + ")";

        try {
            //на созданном стэйтменте вызываем запрос
            statement.execute(createTable);
            connection.commit();
            System.out.println("Table \"users\" is created");
        } catch (SQLException e) {
            connection.commit();
            System.out.println(e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (util.getConnection() !=null){
                util.getConnection().close();
            }
        }

    }

    public void dropUsersTable() throws SQLException, ClassNotFoundException {

        String dropTable = "DROP TABLE IF EXISTS users;";

         try {
            //на созданном стэйтменте вызываем запрос
            statement.execute(dropTable);
            connection.commit();
            System.out.println("Table \"users\" is dropped");
        } catch (SQLException e) {
             connection.commit();
            System.out.println(e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (util.getConnection() !=null){
                util.getConnection().close();
            }
        }

    }

    public void saveUser(String name, String lastName, byte age) throws SQLException, ClassNotFoundException {

        User user = new User(name, lastName, age);
        String insertIntoTable = "INSERT INTO users VALUES ('name', 'lastName', age)";

        try {
            //на созданном стэйтменте вызываем запрос
            statement.executeUpdate(insertIntoTable);
            connection.commit();
            System.out.println("Update of \"users\" is done");

            System.out.println("User  с именем- " + user.getName() + "добавлен в базу данных");
        } catch (SQLException e) {
            connection.commit();
            System.out.println(e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (util.getConnection() !=null){
                util.getConnection().close();
            }
        }

    }

    public void removeUserById(long id) throws ClassNotFoundException, SQLException {

        String insertIntoTable = "DELETE FROM users WHERE 'id' = id;";

        try {
            //на созданном стэйтменте вызываем запрос

            statement.executeUpdate(insertIntoTable);
            connection.commit();
            System.out.println("DELETE by \"id\" is done");
        } catch (SQLException e) {
            connection.commit();
            System.out.println(e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (util.getConnection() !=null){
                util.getConnection().close();
            }
        }

    }

    public List<User> getAllUsers() throws ClassNotFoundException, SQLException {

        String allUsers = "SELECT * FROM users;";

        ResultSet resultSet;
        try {
            //на созданном стэйтменте вызываем запрос
            resultSet = statement.executeQuery(allUsers);
            connection.commit();
        } catch (SQLException e) {
            connection.commit();
            throw new RuntimeException(e);
        }

        //создаю лист юзеров
        List<User> userList = new ArrayList<>();
        //прохожу по всем ячейкам
        while (resultSet.next()) {
            Long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            String lastName = resultSet.getString("lastName");
            Byte age = resultSet.getByte("age");
            //создаю юзера и присваиваю инфу, что получили через резалтсет
            userList.add(new User(name, lastName, age));

//            System.out.println(user);

        }
        if (statement != null) {
            statement.close();
        }
        if (util.getConnection() != null) {
            util.getConnection().close();
        }

        return userList;
    }

    public void cleanUsersTable() throws ClassNotFoundException, SQLException {

        String cleanUsers = "TRUNCATE TABLE users;";

        try {
            //на созданном стэйтменте вызываем запрос
            statement.executeUpdate(cleanUsers);
            connection.commit();
            System.out.println("Clean TABLE \"users\" is done");
        } catch (SQLException e) {
            connection.commit();
            System.out.println(e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (util.getConnection() !=null){
                util.getConnection().close();
            }
        }


    }
}
