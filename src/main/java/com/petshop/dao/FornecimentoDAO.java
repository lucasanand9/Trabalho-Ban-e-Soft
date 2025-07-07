package com.petshop.dao;

import com.petshop.db.DatabaseConnection;
import com.petshop.dados.Fornecimento;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FornecimentoDAO {

    public void adicionar(Fornecimento fornecimento) {
        String sql = "INSERT INTO fornecimento (fornecedor_id, produto_id, data, quantidade, valor_total) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, fornecimento.getId_fornecedor());
            pstmt.setLong(2, fornecimento.getId_produto());
            pstmt.setDate(3, Date.valueOf(fornecimento.getData()));
            pstmt.setInt(4, fornecimento.getQuantidade());
            pstmt.setBigDecimal(5, fornecimento.getValor_total());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removerPorId(long id) {
        String sql = "DELETE FROM fornecimento WHERE id_fornecimento = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Fornecimento> listarTodos() {
        List<Fornecimento> fornecimentos = new ArrayList<>();
        String sql = "SELECT * FROM fornecimento;";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Fornecimento f = new Fornecimento();
                f.setId_fornecimento(rs.getLong("id_fornecimento"));
                f.setId_fornecedor(rs.getLong("fornecedor_id"));
                f.setId_produto(rs.getLong("produto_id"));
                f.setData(rs.getDate("data").toLocalDate());
                f.setQuantidade(rs.getInt("quantidade"));
                f.setValor_total(rs.getBigDecimal("valor_total"));
                fornecimentos.add(f);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fornecimentos;
    }
}
