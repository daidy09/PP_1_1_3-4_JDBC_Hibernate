package jm.task.core.jdbc;
import jm.task.core.jdbc.service.UserServiceImpl;



public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Vasil", "Vasiliev", (byte) 37);
        userService.saveUser("Ivan", "Ivanov", (byte) 23);
        userService.saveUser("Petr", "Petrov", (byte) 18);
        userService.saveUser("Stepan", "Stepanov", (byte) 29);

        userService.getAllUsers();
        userService.removeUserById(1);
        userService.cleanUsersTable();
        userService.dropUsersTable();



    }
}