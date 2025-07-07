package com.petshop.dao;

import com.petshop.dados.Atendimento;
import com.petshop.dados.Funcionario;
import com.petshop.db.DatabaseConnection;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AtendimentoDAO {
    
	public long adicionar(Atendimento atendimento) {
        // A cláusula "RETURNING id_atendimento" é um recurso do PostgreSQL
        // que nos devolve o valor da chave primária que acabou de ser gerada.
        String sql = "INSERT INTO atendimento (funcionario_id, servico_id, data, hora, valor_atendimento) " +
                     "VALUES (?, ?, ?, ?, ?) RETURNING id_atendimento;";
        
        long idGerado = -1;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, atendimento.getId_funcionario());
            pstmt.setLong(2, atendimento.getId_servico());
            pstmt.setDate(3, Date.valueOf(atendimento.getData()));
            pstmt.setTime(4, Time.valueOf(atendimento.getHora()));
            pstmt.setBigDecimal(5, atendimento.getValor_atendimento());

            ResultSet rs = pstmt.executeQuery();

            // Se o resultado tiver uma linha, significa que a inserção funcionou e pegamos o ID.
            if (rs.next()) {
                idGerado = rs.getLong(1);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao adicionar atendimento: " + e.getMessage());
            e.printStackTrace();
        }
        
        return idGerado;
    }
	
	
	public void removerPorId(long id) {
        String sqlOcorrencia = "DELETE FROM ocorrencia WHERE atendimento_id = ?";
        String sqlContrata = "DELETE FROM contrata WHERE atendimento_id = ?";
        String sqlAtendimento = "DELETE FROM atendimento WHERE id_atendimento = ?";

        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);

            try (PreparedStatement pstmt = conn.prepareStatement(sqlOcorrencia)) {
                pstmt.setLong(1, id);
                pstmt.executeUpdate();
            }

            try (PreparedStatement pstmt = conn.prepareStatement(sqlContrata)) {
                pstmt.setLong(1, id);
                pstmt.executeUpdate();
            }

            int affectedRows = 0;
            try (PreparedStatement pstmt = conn.prepareStatement(sqlAtendimento)) {
                pstmt.setLong(1, id);
                affectedRows = pstmt.executeUpdate();
            }

            // A validação acontece aqui. Se o atendimento principal não foi removido,
            // desfazemos as outras deleções.
            if (affectedRows > 0) {
                conn.commit();
                System.out.println(">>> Atendimento e suas dependências removidos com sucesso.");
            } else {
                System.err.println("Aviso: Nenhum atendimento encontrado com o ID " + id + ". Nenhuma remoção foi feita.");
                conn.rollback(); // Desfaz as deleções de ocorrência e contrata
            }

        } catch (SQLException e) {
            System.err.println("Erro ao remover atendimento: " + e.getMessage());
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
	
	public List<Atendimento> listarTodos() {
        List<Atendimento> atendimentos = new ArrayList<>();
        String sql = "SELECT * FROM atendimento;";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Atendimento a = new Atendimento();
                a.setId_atendimento(rs.getLong("id_atendimento"));
                a.setId_funcionario(rs.getLong("funcionario_id"));
                a.setId_servico(rs.getLong("servico_id"));
                a.setData(rs.getDate("data").toLocalDate());
                a.setHora(rs.getTime("hora").toLocalTime());
                a.setValor_atendimento(rs.getBigDecimal("valor_atendimento"));
                atendimentos.add(a);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar atendimentos: " + e.getMessage());
            e.printStackTrace();
        }
        return atendimentos;
    }

 
    public void listarAtendimentosCompletos() {
    	//Faz uma uniao entre as tabelas: Contrata, Atendimento, Animal, Cliente, Funcionario e Servico.
    	//E mostra o Nome do funcionario, nome do animal, nome do cliente, tipo do servico, data e hora do atendimento e o valor
        String sql = "SELECT at.id_atendimento, f.nome AS nome_funcionario, an.nome AS nome_animal, c.nome AS nome_cliente, " +
                "s.tipo, at.data, at.hora, at.valor_atendimento, o.descricao AS descricao_ocorrencia " +
                "FROM contrata co " +
                "JOIN atendimento at ON co.atendimento_id = at.id_atendimento " +
                "JOIN animal an ON co.animal_id = an.id_animal " +
                "JOIN cliente c ON an.cliente_id = c.id_cliente " +
                "JOIN funcionario f ON at.funcionario_id = f.id_funcionario " +
                "JOIN servico s ON at.servico_id = s.id_servico " +
                "LEFT JOIN ocorrencia o ON at.id_atendimento = o.atendimento_id " +
                "ORDER BY at.data, at.hora, at.id_atendimento;";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            System.out.println("\n--- Relatório Completo de Atendimentos ---");
            while (rs.next()) {
            	int idAtendimento = rs.getInt("id_atendimento");
                String nomeFuncionario = rs.getString("nome_funcionario");
                String nomeAnimal = rs.getString("nome_animal");
                String nomeCliente = rs.getString("nome_cliente");
                String tipoServico = rs.getString("tipo");
                Date data = rs.getDate("data");
                Time hora = rs.getTime("hora");
                double valor = rs.getDouble("valor_atendimento");
                String descricaoOcorrencia = rs.getString("descricao_ocorrencia");
                if (descricaoOcorrencia == null) {
                    descricaoOcorrencia = "Nenhuma ocorrência registrada.";
                }

                System.out.printf("ID Atend: %d | Data: %s %s | Serviço: %s | Pet: %s (Dono: %s) | Funcionário: %s | Valor: R$%.2f | Ocorrência: %s\n",
                        idAtendimento, data, hora, tipoServico, nomeAnimal, nomeCliente, nomeFuncionario, valor, descricaoOcorrencia);
            }
            System.out.println("------------------------------------------\n");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Funcionario encontrarFuncionarioMaisAtivo() {
        Funcionario funcionario = null;
        String sql = "SELECT * FROM Funcionario WHERE id_funcionario = (" +
                     "SELECT funcionario_id FROM Atendimento " +
                     "GROUP BY funcionario_id " +
                     "ORDER BY COUNT(*) DESC " +
                     "LIMIT 1)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
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
