package fr.le_campus_numerique.intro_java_spring.entities;

public class Country {
     private int id;
     private String name;
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
