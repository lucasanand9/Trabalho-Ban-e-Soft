package com.petshop.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Esta classe é responsável por centralizar e gerenciar a conexão
 * com o banco de dados PostgreSQL.
 */
public class DatabaseConnection {

    // 1. DADOS DE CONEXÃO
    // Estes são os detalhes para acessar seu banco. São 'final' porque não mudam.
    // E 'static' para que pertençam à classe e não a um objeto específico.
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "udesc"; // <-- COLOQUE SUA SENHA AQUI

    /**
     * Método público e estático para obter uma conexão com o banco de dados.
     * 'static' permite que chamemos o método assim: DatabaseConnection.getConnection()
     * sem precisar criar um objeto: new DatabaseConnection().
     *
     * @return um objeto Connection com a conexão estabelecida ou lança uma exceção.
     */
    public static Connection getConnection() {
        try {
            // 3. O DriverManager usa o driver que importamos para criar a conexão
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            // Se algo der errado (senha errada, banco offline), uma exceção é lançada.
            // Para um app de console, imprimir o erro e parar a execução é uma abordagem simples.
            System.err.println("Falha na conexão com o banco de dados: " + e.getMessage());
            throw new RuntimeException("Não foi possível conectar ao banco de dados.", e);
        }
    }
}