package fr.le_campus_numerique.intro_java_spring.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConfig {

    private static DbConfig instance;
    private Connection connection;

    public static DbConfig getInstance(){
        if (instance == null) instance = new DbConfig();
        return instance;
    }

    private DbConfig() {
    }

    public Connection getConnection(){
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:6603/boardgames", "root", "helloworld");
        } catch (SQLException e) {
            System.err.println(e.getMessage());;
        }
        return this.connection;
    }

}
