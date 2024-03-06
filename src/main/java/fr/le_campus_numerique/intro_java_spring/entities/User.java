package fr.le_campus_numerique.intro_java_spring.entities;

import fr.le_campus_numerique.intro_java_spring.dto.UserDTO;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName = "";

    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName = "";

    @Column(name = "pseudo", length = 30, nullable = false)
    private String pseudo = "";

    @Column(name = "email", length = 30, nullable = false)
    private String email = "";

    @Column(name = "password", nullable = false)
    private String password = "";

    @Column(name = "date_of_birth", nullable = false)
    private Date date_of_birth;

    @Column(name = "avatar", nullable = false)
    private String avatar = "";

    @Column(name = "phone", length = 13, nullable = false)
    private String phone = "";

    @Column(name = "tokens_total", nullable = false)
    private int tokens_total;

    @Column(name = "country_id", nullable = false)
    @JoinTable(name = "countries")
    private int country_id;

    @Column(name = "roles", nullable = false)
    @ElementCollection
    private List<String> roles;

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

    public List<String> getRoles() {
        return this.roles;
    }

    public void setRoles(String role) {
        boolean ok = true;
        for (String roleItem : this.getRoles()) {
            if (roleItem.equals(role)) {
                ok = false;
                break;
            }
        }
        if (ok) {
            this.roles.add(role);
        }
    }

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

    public UserDTO toDto() {
        return new UserDTO(this.pseudo, this.email, this.firstName, this.lastName, this.country_id);

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return this.pseudo;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
