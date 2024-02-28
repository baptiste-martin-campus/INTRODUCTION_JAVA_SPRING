package fr.le_campus_numerique.intro_java_spring.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "countries")
public class Country {

    @Id
     private int id;

    @Column(name = "name", length = 50)
     private String name;

    @Column(name = "legal_age")
    private int legal_age;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLegaAge() {
        return legal_age;
    }

    public void setLegalAge(int legal_age) {
        this.legal_age = legal_age;
    }
}
