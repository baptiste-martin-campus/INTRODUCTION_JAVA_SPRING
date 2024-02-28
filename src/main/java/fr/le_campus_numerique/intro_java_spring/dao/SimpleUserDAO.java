package fr.le_campus_numerique.intro_java_spring.dao;

import org.springframework.stereotype.Component;

@Deprecated
@Component
public class SimpleUserDAO  {
//    private final List<User> users = new ArrayList<>();
//    //@Override
//    public List<User> getAllUsers() {
//        sql = "SELECT * FROM users;";
//        return new ArrayList<>(users);
//    }
//
//    private String sql = "";
//
//    //@Override
//    public UserDTO getUserById(@PathVariable int id) {
//        sql = "SELECT * FROM users WHERE user.id = "+ id +";";
//        for (User user : users) {
//            if (user.getId() == id) {
//                return user;
//            }
//        }
//        return null;
//    }
//
//    //@Override
//    public User addUser(@RequestBody User user) {
//        User newUser = new User();
//        //newUser.setName(user.getName());
//        //newUser.setRoles("user");
//        users.add(newUser);
//        return newUser;
//    }
//
//    @Override
//    public User updateUser(@PathVariable int id, @RequestBody User user) {
//        for (int i = 0; i < users.size(); i++) {
//            if (users.get(i).getId() == id) {
//                User currentUser = users.get(i);
//                //currentUser.setName(user.getName());
//                users.set(i, currentUser);
//                break;
//            }
//        }
//        return user;
//    }
//
//    @Override
//    public void deleteUser(@PathVariable int id) {
//        users.removeIf(user -> user.getId() == id );
//    }

}
