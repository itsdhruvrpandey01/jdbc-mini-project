package com.aurionpro.model.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Connector {
	 private static final String URL = "jdbc:postgresql://localhost:5432/student_db";
	    private static final String USER = "postgres";
	    private static final String PASSWORD = "Jannat@8340";

	    public Connection getConnection() {
	        try {
	            Class.forName("org.postgresql.Driver");
	            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
	            System.out.println("Database connected successfully.");
	            return connection;
	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
}