package com.petshop.dao;

import com.petshop.db.DatabaseConnection;
import com.petshop.dados.Compra;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompraDAO {

    public void adicionar(Compra compra) {
        String sql = "INSERT INTO compra (cliente_id, produto_id, data, hora, quantidade, valor_total) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, compra.getId_cliente());
            pstmt.setLong(2, compra.getId_produto());
            pstmt.setDate(3, Date.valueOf(compra.getData()));
            pstmt.setTime(4, Time.valueOf(compra.getHora()));
            pstmt.setInt(5, compra.getQuantidade());
            pstmt.setBigDecimal(6, compra.getValor_total());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removerPorId(long id) {
        String sql = "DELETE FROM compra WHERE id_compra = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Compra> listarTodos() {
        List<Compra> compras = new ArrayList<>();
        String sql = "SELECT * FROM compra;";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Compra c = new Compra();
                c.setId_compra(rs.getLong("id_compra"));
                c.setId_cliente(rs.getLong("cliente_id"));
                c.setId_produto(rs.getLong("produto_id"));
                c.setData(rs.getDate("data").toLocalDate());
                c.setHora(rs.getTime("hora").toLocalTime());
                c.setQuantidade(rs.getInt("quantidade"));
                c.setValor_total(rs.getBigDecimal("valor_total"));
                compras.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return compras;
    }
}