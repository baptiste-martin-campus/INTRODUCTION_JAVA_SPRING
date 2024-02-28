package fr.le_campus_numerique.intro_java_spring.controllers;

import fr.le_campus_numerique.intro_java_spring.dto.UserDTO;
import fr.le_campus_numerique.intro_java_spring.entities.User;
import fr.le_campus_numerique.intro_java_spring.interfaces.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    //@Qualifier("simpleUserDAO")
    //@Qualifier("mySQLUserDAO")
    @Autowired
    private UserDAO dao;

    @GetMapping("/users")
    public List<UserDTO> getAllUsers() {
        return dao.getAllUsers().stream()
                .map(User::toDto)
                .toList();
    }


    @GetMapping("/users/{id}")
    public UserDTO getUserById(@PathVariable int id) {
        return dao.getUserById(id).toDto();
    }

    @PostMapping("/users")
    public UserDTO addUser(@RequestBody User user) {
        return dao.addUser(user).toDto();
    }

    @PutMapping("/users/{id}")
    public UserDTO updateUser(@PathVariable int id, @RequestBody User user) {
        return dao.updateUser(id, user).toDto();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        dao.deleteUser(id);
    }
}