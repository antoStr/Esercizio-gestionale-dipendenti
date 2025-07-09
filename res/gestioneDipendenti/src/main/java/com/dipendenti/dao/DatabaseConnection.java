package com.dipendenti.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	private static final String url = "jdbc:mysql://localhost:3306/gestionaledipendenti";
	private static final String username = "root";
	private static final String password = "root";
	
	static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver MySQL caricato correttamente");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver MySQL non trovato!");
            e.printStackTrace();
        }
    }
	
	public static Connection getConnection() {
		Connection conn = null;
		
		
		try {
	        // Aggiungi questo per vedere se riesce a connettersi
	        System.out.println("Tentativo connessione a: " + url);
	        conn = DriverManager.getConnection(url, username, password);
	        System.out.println("Connessione riuscita!");
	        
	    } catch (SQLException e) {
	        System.err.println("Errore nella connessione del database:");
	        System.err.println("Messaggio: " + e.getMessage());
	        System.err.println("Codice errore: " + e.getErrorCode());
	        e.printStackTrace(); // Questo ti darà più dettagli
	    }
	    
		
		return conn;
	}
	
}
