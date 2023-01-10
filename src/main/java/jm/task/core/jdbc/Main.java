package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;



import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {


        UserDao userDao = new UserDaoJDBCImpl();

        userDao.createUsersTable();
        userDao.saveUser("Vasil", "Vasiliev", (byte) 37);
        userDao.saveUser("Ivan", "Ivanov", (byte) 23);
        userDao.saveUser("Petr", "Petrov", (byte) 18);
        userDao.saveUser("Stepan", "Stepanov", (byte) 29);

        userDao.getAllUsers();
        userDao.removeUserById(1);
        userDao.cleanUsersTable();
        userDao.dropUsersTable();



    }
}