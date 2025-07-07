package com.petshop.dao;

import com.petshop.db.DatabaseConnection;
import com.petshop.dados.Fornecedor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FornecedorDAO {

    public void adicionar(Fornecedor fornecedor) {
        String sql = "INSERT INTO fornecedor (cnpj, nome, cep, numero, complemento) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, fornecedor.getCnpj());
            pstmt.setString(2, fornecedor.getNome());
            pstmt.setString(3, fornecedor.getCep());
            pstmt.setString(4, fornecedor.getNumero());
            pstmt.setString(5, fornecedor.getComplemento());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removerPorId(long id) {
        String sqlFornecimento = "DELETE FROM fornecimento WHERE fornecedor_id = ?";
        String sqlFornecedor = "DELETE FROM fornecedor WHERE id_fornecedor = ?";
        
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);

            try (PreparedStatement pstmt = conn.prepareStatement(sqlFornecimento)) {
                pstmt.setLong(1, id);
                pstmt.executeUpdate();
            }

            int affectedRows = 0;
            try (PreparedStatement pstmt = conn.prepareStatement(sqlFornecedor)) {
                pstmt.setLong(1, id);
                affectedRows = pstmt.executeUpdate();
            }
            
            conn.commit();

            if (affectedRows > 0) {
                System.out.println(">>> Fornecedor e seus registros de fornecimento foram removidos com sucesso!");
            } else {
                System.err.println("Aviso: Nenhum fornecedor encontrado com o ID " + id + ".");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao remover fornecedor. A transação será desfeita.");
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<Fornecedor> listarTodos() {
        List<Fornecedor> fornecedores = new ArrayList<>();
        String sql = "SELECT * FROM fornecedor;";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Fornecedor f = new Fornecedor();
                f.setId_fornecedor(rs.getLong("id_fornecedor"));
                f.setCnpj(rs.getString("cnpj"));
                f.setNome(rs.getString("nome"));
                f.setCep(rs.getString("cep"));
                f.setNumero(rs.getString("numero"));
                f.setComplemento(rs.getString("complemento"));
                fornecedores.add(f);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fornecedores;
    }
    
    public Fornecedor buscarPorId(long id) {
        String sql = "SELECT * FROM fornecedor WHERE id_fornecedor = ?";
        Fornecedor fornecedor = null;
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                fornecedor = new Fornecedor();
                fornecedor.setId_fornecedor(rs.getLong("id_fornecedor"));
                fornecedor.setCnpj(rs.getString("cnpj"));
                fornecedor.setNome(rs.getString("nome"));
                fornecedor.setCep(rs.getString("cep"));
                fornecedor.setNumero(rs.getString("numero"));
                fornecedor.setComplemento(rs.getString("complemento"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fornecedor;
    }

}