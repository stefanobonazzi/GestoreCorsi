package it.polito.tdp.corsi.db;

import java.sql.*;

public class DBConnect {

	public static Connection getConnection() {
		try {		
			String url = "jdbc:mysql://localhost/iscritticorsi?user=stefanobonazzi&password=psw2810";
			return DriverManager.getConnection(url);
		} catch (SQLException e) {
			System.out.println("Errore di connessione!");
			e.printStackTrace();
			return null;
		}
	}
	
}
