package com.petshop.dao;

import com.petshop.db.DatabaseConnection;
import com.petshop.dados.Servico;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicoDAO {

    public void adicionar(Servico servico) {
        String sql = "INSERT INTO servico (tipo, preco, tempo_duracao_minutos) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, servico.getTipo());
            pstmt.setBigDecimal(2, servico.getPreco());
            pstmt.setLong(3, servico.getDuracao());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removerPorId(long id) {
        String sql = "DELETE FROM servico WHERE id_servico = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Servico> listarTodos() {
        List<Servico> servicos = new ArrayList<>();
        String sql = "SELECT * FROM servico;";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Servico s = new Servico();
                s.setId_servico(rs.getLong("id_servico"));
                s.setTipo(rs.getString("tipo"));
                s.setPreco(rs.getBigDecimal("preco"));
                s.setDuracao((rs.getLong("tempo_duracao_minutos")));					
                servicos.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return servicos;
    }
    
    public Servico buscarPorId(long id) {
        String sql = "SELECT * FROM servico WHERE id_servico = ?";
        Servico servico = null;
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                servico = new Servico();
                servico.setId_servico(rs.getLong("id_servico"));
                servico.setTipo(rs.getString("tipo"));
                servico.setPreco(rs.getBigDecimal("preco"));
                servico.setDuracao(rs.getInt("tempo_duracao_minutos"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return servico;
    }
}