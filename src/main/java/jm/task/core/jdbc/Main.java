package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserServiceImpl;


import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserServiceImpl userService = new UserServiceImpl();


//        UserDao userDao = new UserDaoJDBCImpl();
//
        userService.createUsersTable();
//        userService.saveUser("Vasil", "Vasiliev", (byte) 37);
//        userService.saveUser("Ivan", "Ivanov", (byte) 23);
//        userService.saveUser("Petr", "Petrov", (byte) 18);
//        userService.saveUser("Stepan", "Stepanov", (byte) 29);
//
//        userService.getAllUsers();
//        userService.removeUserById(1);
//        userService.cleanUsersTable();
//        userService.dropUsersTable();



    }
}