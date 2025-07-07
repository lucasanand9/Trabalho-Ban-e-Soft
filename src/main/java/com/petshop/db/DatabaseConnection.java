package com.petshop.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "udesc"; // <-- COLOQUE SUA SENHA AQUI

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Falha na conexão com o banco de dados: " + e.getMessage());
            throw new RuntimeException("Não foi possível conectar ao banco de dados.", e);
        }
    }
}