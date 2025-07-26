package com.aurionpro.model.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Connector {

	private Connection connection = null;
	private Statement statement = null;
 
	public void connect() {
		try {
			Class.forName("org.postgresql.Driver");
 
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/student_db", "postgres", "Jannat@8340");
 
			statement = connection.createStatement();
 
			System.out.println("Database connected");
 
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}