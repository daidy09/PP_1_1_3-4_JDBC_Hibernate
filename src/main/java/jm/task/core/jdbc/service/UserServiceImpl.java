package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
    private User user = new User();


    public UserServiceImpl() throws SQLException, ClassNotFoundException {
    }

    public void createUsersTable() {
        userDaoJDBC.createUsersTable();

    }

    public void dropUsersTable() {
        userDaoJDBC.dropUsersTable();

    }

    public void saveUser(String name, String lastName, byte age) {
//        user.setName(name);
//        user.setLastName(lastName);
//        user.setAge(age);
        userDaoJDBC.saveUser(user.getName(), user.getLastName(), user.getAge());

    }

    public void removeUserById(long id) throws SQLException, ClassNotFoundException {
//        user.setId(id);
        userDaoJDBC.removeUserById(user.getId());

    }

    public List<User> getAllUsers() throws SQLException {
        return userDaoJDBC.getAllUsers();
    }

    public void cleanUsersTable() {
        userDaoJDBC.cleanUsersTable();

    }
}
