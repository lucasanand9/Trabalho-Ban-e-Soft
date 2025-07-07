package com.petshop.dao;

import com.petshop.db.DatabaseConnection;
import com.petshop.dados.Ocorrencia;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OcorrenciaDAO {

    public void adicionar(Ocorrencia ocorrencia) {
        String sql = "INSERT INTO ocorrencia (atendimento_id, descricao) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, ocorrencia.getId_atendimento());
            pstmt.setString(2, ocorrencia.getDescricao());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removerPorId(long id) {
        String sql = "DELETE FROM ocorrencia WHERE id_ocorrencia = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Ocorrencia> listarTodos() {
        List<Ocorrencia> ocorrencias = new ArrayList<>();
        String sql = "SELECT * FROM ocorrencia;";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Ocorrencia o = new Ocorrencia();
                o.setId_ocorrencia(rs.getLong("id_ocorrencia"));
                o.setId_atendimento(rs.getLong("atendimento_id"));
                o.setDescricao(rs.getString("descricao"));
                ocorrencias.add(o);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ocorrencias;
    }
}