package com.petshop.dao;

import com.petshop.db.DatabaseConnection;
import com.petshop.dados.Contrata;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContrataDAO {

    public void adicionar(long atendimentoId, long animalId) {
        String sql = "INSERT INTO contrata (atendimento_id, animal_id) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, atendimentoId);
            pstmt.setLong(2, animalId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remover(long atendimentoId, long animalId) {
        String sql = "DELETE FROM contrata WHERE atendimento_id = ? AND animal_id = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, atendimentoId);
            pstmt.setLong(2, animalId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Contrata> listarTodos() {
        List<Contrata> contratacoes = new ArrayList<>();
        String sql = "SELECT * FROM contrata;";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Contrata c = new Contrata();
                c.setId_atendimento(rs.getLong("atendimento_id"));
                c.setId_animal(rs.getLong("animal_id"));
                contratacoes.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contratacoes;
    }
}