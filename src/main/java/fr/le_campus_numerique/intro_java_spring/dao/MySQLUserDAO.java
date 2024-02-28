package fr.le_campus_numerique.intro_java_spring.dao;

import fr.le_campus_numerique.intro_java_spring.entities.User;
import fr.le_campus_numerique.intro_java_spring.interfaces.UserDAO;
import fr.le_campus_numerique.intro_java_spring.services.DbConfig;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class MySQLUserDAO implements UserDAO {
    private String sql = "";
    private final DbConfig db = DbConfig.getInstance();

    private User getDaUser(ResultSet res) throws SQLException {
        User user = new User();
        user.setId(res.getInt("id"));
        user.setFirstName(res.getString("first_name"));
        user.setLastName(res.getString("last_name"));
        user.setPseudo(res.getString("pseudo"));
        user.setEmail(res.getString("email"));
        user.setPhone(res.getString("phone"));
        user.setAvatar(res.getString("avatar"));
        user.setDate_of_birth(res.getDate("date_of_birth"));
        user.setTotalTokens(res.getInt("tokens_total"));
        user.setCountry_id(res.getInt("country_id"));
        return user;
    }

    public List<User> getAllUsers() {
        try {
            List<User> users = new ArrayList<>();
            sql = "SELECT * FROM users;";
            Connection conn = this.db.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                User user = getDaUser(res);
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public User getUserById(int id) {
        sql = "SELECT * FROM users WHERE id = " + id + ";";
        try {
            Connection conn = this.db.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery(sql);
            while (res.next()) {
                return getDaUser(res);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public User addUser(User user) {
        try {
            Connection conn = this.db.getConnection();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (first_name, last_name, pseudo, email, date_of_birth, avatar, phone, tokens_total, country_id) VALUES(?,?,?,?,?,?,?,?,?);");
            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getPseudo());
            stmt.setString(4, user.getEmail());
            stmt.setDate(5, user.getDate_of_birth());
            stmt.setString(6, user.getAvatar());
            stmt.setString(7, user.getPhone());
            stmt.setInt(8, user.getTotalTokens());
            stmt.setInt(9, user.getCountry_id());
            int res = stmt.executeUpdate();
            System.out.println(res + "lines added");
            return user;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public User updateUser(int id, User user) {

        //sql = "UPDATE users SET name="+ user.getFirstName() + "WHERE id="+ id +";";
        try {
            Connection conn = this.db.getConnection();
            PreparedStatement stmt = conn.prepareStatement("UPDATE users SET first_name = ?, last_name = ?, pseudo = ?, email = ?, date_of_birth = ?, avatar = ? , phone = ?, tokens_total = ?, country_id = ? WHERE id = ?;");
            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getPseudo());
            stmt.setString(4, user.getEmail());
            stmt.setDate(5, user.getDate_of_birth());
            stmt.setString(6, user.getAvatar());
            stmt.setString(7, user.getPhone());
            stmt.setInt(8, user.getTotalTokens());
            stmt.setInt(9, user.getCountry_id());
            stmt.setInt(10, id);
            int rawCount = stmt.executeUpdate();
            System.out.println(rawCount);
            return user;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public void deleteUser(int id) {
        try {
            Connection conn = this.db.getConnection();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM users WHERE id=" + id + ";");
            int res = stmt.executeUpdate();
            System.out.println(res + " user deleted");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
