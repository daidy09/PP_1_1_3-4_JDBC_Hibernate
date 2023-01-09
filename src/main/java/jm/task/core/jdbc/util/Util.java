package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    // реализуйте настройку соеденения с БД

    private static final String DRIVER= "com.mysql.cj.jdbc.Driver";
    private static final String URL="jdbc:mysql://localhost:3306/mydbtest";
    private static final String User= "root";
    private static final String PASSWORD = "root";

    public Util() throws SQLException, ClassNotFoundException {
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {

        Connection connection = null;
        connection.setAutoCommit(false);
        Class.forName(DRIVER);//получаем конкретный драйвер
        try {
            connection = DriverManager.getConnection(URL,User,PASSWORD);//создаем физический коннекшн
            System.out.println("Connection OK");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }



}
