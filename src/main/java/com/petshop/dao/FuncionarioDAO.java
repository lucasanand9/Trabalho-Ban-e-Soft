package com.petshop.dao;

import com.petshop.db.DatabaseConnection;
import com.petshop.dados.Funcionario; // Use o nome do seu pacote de dados
import java.sql.*;
import java.util.List; // Já vamos importar para o próximo método
import java.util.ArrayList; // Já vamos importar para o próximo método


public class FuncionarioDAO {
	
	public void adicionar(Funcionario funcionario) {
		String sql = "INSERT INTO funcionario (cpf, nome, data_nascimento, salario) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, funcionario.getCpf());
            pstmt.setString(2, funcionario.getNome());
            pstmt.setDate(3, Date.valueOf(funcionario.getData_nascimento())); // Converte LocalDate para o tipo SQL Date
            pstmt.setBigDecimal(4, funcionario.getSalario());

            pstmt.executeUpdate();
            System.out.println("Funcionário " + funcionario.getNome() + " adicionado com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao inserir funcionário: " + e.getMessage());
        }
		
	}
	
    public List<Funcionario> listarTodos() {
        List<Funcionario> funcionarios = new ArrayList<>();
        String sql = "SELECT * FROM funcionario;";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Funcionario funcionario = new Funcionario();

                funcionario.setId_funcionario(rs.getInt("id_funcionario"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setData_nascimento(rs.getDate("data_nascimento").toLocalDate()); // Converte o Date do SQL para LocalDate
                funcionario.setSalario(rs.getBigDecimal("salario")); 

                funcionarios.add(funcionario);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar funcionários: " + e.getMessage());
        }

        return funcionarios;
    }
    
    public void removerPorId(long id) {
        String sql = "DELETE FROM funcionario WHERE id_funcionario = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setLong(1, id);
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) {
                System.out.println(">>> Funcionário removido com sucesso!");
            } else {
                System.err.println("Aviso: Nenhum funcionário encontrado com o ID " + id + ". Nenhuma remoção foi feita.");
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao remover funcionário: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public Funcionario buscarPorId(long id) {
        String sql = "SELECT * FROM funcionario WHERE id_funcionario = ?";
        Funcionario funcionario = null;
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                funcionario = new Funcionario();
                funcionario.setId_funcionario(rs.getLong("id_funcionario"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setData_nascimento(rs.getDate("data_nascimento").toLocalDate());
                funcionario.setSalario(rs.getBigDecimal("salario"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return funcionario;
    }

	
}
