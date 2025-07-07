package com.petshop.dao;

import com.petshop.db.DatabaseConnection;
import com.petshop.dados.Produto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    public void adicionar(Produto produto) {
        String sql = "INSERT INTO produto (nome, tipo, valor_venda) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, produto.getNome());
            pstmt.setString(2, produto.getTipo());
            pstmt.setBigDecimal(3, produto.getValor());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removerPorId(long id) {
        String sql = "DELETE FROM produto WHERE id_produto = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Produto> listarTodos() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produto;";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Produto p = new Produto();
                p.setId_produto(rs.getLong("id_produto"));
                p.setNome(rs.getString("nome"));
                p.setTipo(rs.getString("tipo"));
                p.setValor(rs.getBigDecimal("valor_venda"));
                produtos.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produtos;
    }
    
    public Produto buscarPorId(long id) {
        String sql = "SELECT * FROM produto WHERE id_produto = ?";
        Produto produto = null;
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                produto = new Produto();
                produto.setId_produto(rs.getLong("id_produto"));
                produto.setNome(rs.getString("nome"));
                produto.setTipo(rs.getString("tipo"));
                produto.setValor(rs.getBigDecimal("valor_venda"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produto;
    }
}
