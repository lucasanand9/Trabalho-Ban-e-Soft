package com.petshop.dao;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.petshop.dados.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

class AtendimentoDAOTest {

    // Precisamos de todos os DAOs envolvidos na transação
    private static AtendimentoDAO atendimentoDAO = new AtendimentoDAO();
    private static ContrataDAO contrataDAO = new ContrataDAO();
    private static ClienteDAO clienteDAO = new ClienteDAO();
    private static AnimalDAO animalDAO = new AnimalDAO();
    private static FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    private static ServicoDAO servicoDAO = new ServicoDAO();
    
    // Objetos de teste que serão criados e depois removidos
    private static Cliente clienteTeste;
    private static Animal animalTeste;
    private static Funcionario funcionarioTeste;
    private static Servico servicoTeste;
    private static Atendimento atendimentoTeste;

    @BeforeAll
    static void setUp() {
        // PREPARAÇÃO: Cria todas as entidades necessárias para um atendimento existir.
        clienteTeste = new Cliente();
        clienteTeste.setCpf("77777777777");
        clienteTeste.setNome("Cliente para Atendimento Teste");
        clienteDAO.adicionar(clienteTeste);
        // Pega o ID gerado
        clienteTeste.setId_cliente(clienteDAO.listarTodos().stream().filter(c -> c.getCpf().equals("77777777777")).findFirst().get().getId_cliente());

        animalTeste = new Animal();
        animalTeste.setId_cliente(clienteTeste.getId_cliente());
        animalTeste.setNome("Pet para Atendimento Teste");
        animalDAO.adicionar(animalTeste);
        // Pega o ID gerado
        animalTeste.setId_animal(animalDAO.listarTodos().stream().filter(a -> a.getNome().equals("Pet para Atendimento Teste")).findFirst().get().getId_animal());

        funcionarioTeste = new Funcionario();
        funcionarioTeste.setCpf("66666666666");
        funcionarioTeste.setNome("Funcionario para Atendimento Teste");
        funcionarioTeste.setSalario(new BigDecimal("1000"));
        funcionarioTeste.setData_nascimento(LocalDate.now());
        funcionarioDAO.adicionar(funcionarioTeste);
        // Pega o ID gerado
        funcionarioTeste.setId_funcionario(funcionarioDAO.listarTodos().stream().filter(f -> f.getCpf().equals("66666666666")).findFirst().get().getId_funcionario());

        servicoTeste = new Servico();
        servicoTeste.setTipo("Serviço de Teste");
        servicoTeste.setPreco(new BigDecimal("99.99"));
        servicoTeste.setDuracao(60);
        servicoDAO.adicionar(servicoTeste);
        // Pega o ID gerado
        servicoTeste.setId_servico(servicoDAO.listarTodos().stream().filter(s -> s.getTipo().equals("Serviço de Teste")).findFirst().get().getId_servico());
    }

    @Test
    void testRegistrarAtendimentoCompleto() {
        // ARRANGE: Configura o objeto Atendimento com os IDs das entidades que criamos.
        atendimentoTeste = new Atendimento();
        atendimentoTeste.setId_funcionario(funcionarioTeste.getId_funcionario());
        atendimentoTeste.setId_servico(servicoTeste.getId_servico());
        atendimentoTeste.setData(LocalDate.now());
        atendimentoTeste.setHora(LocalTime.now());
        atendimentoTeste.setValor_atendimento(new BigDecimal("120.00"));

        // ACT: Executa a transação em duas partes, como na Main.
        long idNovoAtendimento = atendimentoDAO.adicionar(atendimentoTeste);
        atendimentoTeste.setId_atendimento(idNovoAtendimento); // Guarda o ID gerado
        
        // Pré-condição para a segunda parte
        assertTrue(idNovoAtendimento > 0, "A inserção na tabela Atendimento falhou, nenhum ID foi retornado.");

        contrataDAO.adicionar(idNovoAtendimento, animalTeste.getId_animal());

        // ASSERT: Verifica se a ligação foi criada na tabela 'contrata'.
        boolean ligacaoEncontrada = false;
        for (Contrata c : contrataDAO.listarTodos()) {
            if (c.getId_atendimento() == idNovoAtendimento && c.getId_animal() == animalTeste.getId_animal()) {
                ligacaoEncontrada = true;
                break;
            }
        }
        assertTrue(ligacaoEncontrada, "A ligação na tabela 'contrata' não foi criada corretamente.");
    }

    @AfterAll
    static void tearDown() {
        // LIMPEZA: Remove tudo na ordem inversa para não violar as chaves estrangeiras.
        if (atendimentoTeste != null && atendimentoTeste.getId_atendimento() > 0) {
            atendimentoDAO.removerPorId(atendimentoTeste.getId_atendimento());
        }
        if (animalTeste != null && animalTeste.getId_animal() > 0) {
            animalDAO.removerPorId(animalTeste.getId_animal());
        }
        if (clienteTeste != null && clienteTeste.getId_cliente() > 0) {
            clienteDAO.removerPorId(clienteTeste.getId_cliente());
        }
        if (servicoTeste != null && servicoTeste.getId_servico() > 0) {
            servicoDAO.removerPorId(servicoTeste.getId_servico());
        }
        if (funcionarioTeste != null && funcionarioTeste.getId_funcionario() > 0) {
            funcionarioDAO.removerPorId(funcionarioTeste.getId_funcionario());
        }
    }
}