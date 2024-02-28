package fr.le_campus_numerique.intro_java_spring.entities;

import fr.le_campus_numerique.intro_java_spring.dto.UserDTO;

import java.sql.Date;

public class User {

    private int id;
    private String firstName = "";
    private String lastName = "";
    private String pseudo = "";
    private String email = "";
    private Date date_of_birth;
    private String avatar = "";
    private String phone = "";
    private int tokens_total;
    private int country_id;

    //private final List<String> roles = new ArrayList<>();

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPseudo() {
        return this.pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

//    public List<String> getRoles() {
//        return this.roles;
//    }
//
//    public void setRoles(String role) {
//        boolean ok = true;
//        for (String roleItem : this.getRoles()) {
//            if (roleItem.equals(role)) {
//                ok = false;
//                break;
//            }
//        }
//        if (ok) {
//            this.roles.add(role);
//        }
//    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTotalTokens() {
        return tokens_total;
    }

    public void setTotalTokens(int tokens_total) {
        this.tokens_total = tokens_total;
    }

    public int getCountry_id() {
        return country_id;
    }

    public void setCountry_id(int id) {
        this.country_id = id;
    }

    public UserDTO toDto(){
        return new UserDTO(this.pseudo, this.email, this.firstName, this.lastName, this.country_id);

    }

}
