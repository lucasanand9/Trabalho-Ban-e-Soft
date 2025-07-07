package com.petshop.dao;

import com.petshop.dados.Cliente;
import com.petshop.db.DatabaseConnection;
import java.sql.*;
import java.util.List; // Já vamos importar para o próximo método

import java.util.ArrayList; // Já vamos importar para o próximo método

public class ClienteDAO {
	public void adicionar(Cliente cliente) {
		String sql = "insert into cliente (nome, cpf, telefone) values (?, ?, ?)";
		
		try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			  pstmt.setString(1, cliente.getNome());
			  pstmt.setString(2, cliente.getCpf());
			  pstmt.setString(3, cliente.getTelefone());
			  
			  pstmt.executeUpdate();
			  System.out.println("Cliente " + cliente.getNome() + " foi adicionado!");
			  
		  }catch (Exception e) {
			  System.err.println("Erro ao inserir cliente: " + e.getMessage());
		}
	}
	
	 public List<Cliente> listarTodos() {
	        List<Cliente> clientes = new ArrayList<>();
	        String sql = "SELECT * FROM cliente ORDER BY nome;";
	        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
	            while (rs.next()) {
	                Cliente c = new Cliente();
	                c.setId_cliente(rs.getLong("id_cliente"));
	                c.setCpf(rs.getString("cpf"));
	                c.setNome(rs.getString("nome"));
	                c.setTelefone(rs.getString("telefone"));
	                clientes.add(c);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return clientes;
	    }
	
	 public void removerPorId(long id) {
	        String sql = "DELETE FROM cliente WHERE id_cliente = ?";
	        try (Connection conn = DatabaseConnection.getConnection();
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            
	            pstmt.setLong(1, id);
	            
	            // Captura o número de linhas afetadas
	            int affectedRows = pstmt.executeUpdate();
	            
	            // Verifica se alguma linha foi realmente deletada
	            if (affectedRows > 0) {
	                System.out.println(">>> Cliente removido com sucesso!");
	            } else {
	                System.err.println("Aviso: Nenhum cliente encontrado com o ID " + id + ". Nenhuma remoção foi feita.");
	            }
	            
	        } catch (SQLException e) {
	            System.err.println("Erro ao remover cliente: " + e.getMessage());
	            e.printStackTrace();
	        }
	    }
	
	public Cliente buscarPorId(long id) {
        String sql = "SELECT * FROM cliente WHERE id_cliente = ?";
        Cliente cliente = null;
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                cliente = new Cliente();
                cliente.setId_cliente(rs.getLong("id_cliente"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setNome(rs.getString("nome"));
                cliente.setTelefone(rs.getString("telefone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;
    }

}
