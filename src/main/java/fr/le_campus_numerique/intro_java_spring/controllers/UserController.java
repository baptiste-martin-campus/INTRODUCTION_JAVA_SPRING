package fr.le_campus_numerique.intro_java_spring.controllers;

import fr.le_campus_numerique.intro_java_spring.dao.UserRepository;
import fr.le_campus_numerique.intro_java_spring.dto.UserDTO;
import fr.le_campus_numerique.intro_java_spring.entities.Roles;
import fr.le_campus_numerique.intro_java_spring.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController{

    //@Qualifier("simpleUserDAO")
    //@Qualifier("mySQLUserDAO")
//    @Autowired
//    private UserDAO dao;

    @Autowired
    private UserRepository dao;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Secured(Roles.ROLE_ADMIN)
    @GetMapping("/users")
    public List<UserDTO> getAllUsers() {
        LOGGER.info("Affichage de tous les users");
        ArrayList<UserDTO> users = new ArrayList<>();
        for (User user : dao.findAll()) {
            users.add(user.toDto());
        }
            return users;
    }

    @Secured(Roles.ROLE_USER)
    @GetMapping("/users/{id}")
    public UserDTO getUserById(@PathVariable int id) {
        LOGGER.info("Affichage de l'user " + id);
        return dao.findById(id).get().toDto();
    }

    @GetMapping("/users/name/{name}")
    public UserDTO getUserByName(@PathVariable String name) {
        LOGGER.info("Affichage de l'user avec un name" + name);
        return dao.findByPseudo(name).toDto();
    }


    @PostMapping("/users")
    public UserDTO addUser(@RequestBody User user) throws Exception{
        LOGGER.info("Ajout d'un user");
        User userAlreadyCreated = dao.findByPseudo(user.getPseudo());
        if(userAlreadyCreated != null ){
            LOGGER.error("Erreur : User déjà crée");
            throw new Exception();
        }
        String newPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(newPassword);
        user.setRoles("ROLE_USER");
        return dao.save(user).toDto();
    }

    @PutMapping("/users/{id}")
    public UserDTO updateUser(@PathVariable int id, @RequestBody User user) throws Exception {
        LOGGER.info("Mise à jour de l'user "+ id);
        User ancientUser = dao.findById(id).orElse(null);
        if (ancientUser == null){
            LOGGER.error("Erreur : User inconnu avec l'id donné");
            throw new Exception();
        }
        ancientUser.setAvatar(user.getAvatar());
        ancientUser.setFirstName(user.getFirstName());
        ancientUser.setLastName(user.getLastName());
        ancientUser.setPseudo(user.getPseudo());
        ancientUser.setEmail(user.getEmail());
        ancientUser.setDate_of_birth(user.getDate_of_birth());
        ancientUser.setPhone(user.getPhone());
        ancientUser.setTotalTokens(user.getTotalTokens());
        ancientUser.setCountry_id(user.getCountry_id());

        String newPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(newPassword);
        ancientUser.setPassword(passwordEncoder.encode(newPassword));
        return dao.save(ancientUser).toDto();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        LOGGER.info("Suppression de l'user " + id);
        dao.deleteById(id);
    }
}