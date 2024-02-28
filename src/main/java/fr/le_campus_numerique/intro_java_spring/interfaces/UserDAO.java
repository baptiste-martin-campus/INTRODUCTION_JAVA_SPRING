package fr.le_campus_numerique.intro_java_spring.interfaces;

import fr.le_campus_numerique.intro_java_spring.entities.User;

import java.util.List;

public interface UserDAO {
    List<User> getAllUsers();
    User getUserById(int id);
    User addUser(User user);
    User updateUser(int id, User user);
    void deleteUser(int id);
}
