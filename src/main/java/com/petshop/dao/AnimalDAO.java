package com.petshop.dao;

import com.petshop.db.DatabaseConnection;
import com.petshop.dados.Animal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnimalDAO {

    public void adicionar(Animal animal) {
        String sql = "INSERT INTO animal (cliente_id, nome, raca, idade, tamanho) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, animal.getId_cliente());
            pstmt.setString(2, animal.getNome());
            pstmt.setString(3, animal.getRaca());
            pstmt.setInt(4, animal.getIdade());
            pstmt.setString(5, animal.getTamanho());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removerPorId(long id) {
        String sql = "DELETE FROM animal WHERE id_animal = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setLong(1, id);
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) {
                System.out.println(">>> Animal removido com sucesso!");
            } else {
                System.err.println("Aviso: Nenhum animal encontrado com o ID " + id + ". Nenhuma remoção foi feita.");
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao remover animal: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Animal> listarTodos() {
        List<Animal> animais = new ArrayList<>();
        String sql = "SELECT * FROM animal;";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Animal a = new Animal();
                a.setId_animal(rs.getLong("id_animal"));
                a.setId_cliente(rs.getLong("cliente_id"));
                a.setNome(rs.getString("nome"));
                a.setRaca(rs.getString("raca"));
                a.setIdade(rs.getInt("idade"));
                a.setTamanho(rs.getString("tamanho"));
                animais.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return animais;
    }
    
    public Animal buscarPorId(long id) {
        String sql = "SELECT * FROM animal WHERE id_animal = ?";
        Animal animal = null;
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                animal = new Animal();
                animal.setId_animal(rs.getLong("id_animal"));
                animal.setId_cliente(rs.getLong("cliente_id"));
                animal.setNome(rs.getString("nome"));
                animal.setRaca(rs.getString("raca"));
                animal.setIdade(rs.getInt("idade"));
                animal.setTamanho(rs.getString("tamanho"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return animal;
    }
}