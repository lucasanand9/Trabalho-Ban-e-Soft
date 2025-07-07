package com.petshop.dao;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import com.petshop.dados.Cliente;
import java.util.List;

@TestMethodOrder(OrderAnnotation.class)
class ClienteDAOTest {

    private static ClienteDAO clienteDAO = new ClienteDAO();
    private static Cliente clienteDeTeste = new Cliente();

    @BeforeAll
    static void setUp() {
        System.out.println("Iniciando a suíte de testes para ClienteDAO...");
        clienteDeTeste.setCpf("99999999999");
        clienteDeTeste.setNome("Cliente de Teste JUnit");
        clienteDeTeste.setTelefone("47999999999");
    }

    /**
     * TESTE 1: Verifica se um novo cliente pode ser adicionado.
     */
    @Test
    @Order(1)
    void testAdicionar() {
        System.out.println("Executando testeAdicionar...");
        clienteDAO.adicionar(clienteDeTeste);
    }

    /**
     * TESTE 2: Verifica se a listagem de clientes funciona e se o cliente
     * adicionado no teste anterior está na lista.
     */
    @Test
    @Order(2)
    void testListarTodosEEncontrarCliente() {
        System.out.println("Executando testeListarTodos...");
        List<Cliente> clientes = clienteDAO.listarTodos();

        assertNotNull(clientes, "A lista de clientes não deveria ser nula.");
        assertFalse(clientes.isEmpty(), "A lista de clientes não deveria estar vazia após a adição.");

        // Procura pelo cliente de teste na lista e guarda seu ID gerado pelo banco.
        boolean clienteEncontrado = false;
        for (Cliente c : clientes) {
            if (c.getCpf().equals(clienteDeTeste.getCpf())) {
                clienteEncontrado = true;
                clienteDeTeste.setId_cliente(c.getId_cliente());
                break;
            }
        }
        assertTrue(clienteEncontrado, "O cliente de teste não foi encontrado na lista.");
    }

    /**
     * TESTE 3: Verifica se o método buscarPorId encontra o cliente de teste.
     */
    @Test
    @Order(3)
    void testBuscarPorId() {
        System.out.println("Executando testeBuscarPorId...");
        assertTrue(clienteDeTeste.getId_cliente() > 0, "O ID do cliente de teste é inválido.");
        Cliente clienteEncontrado = clienteDAO.buscarPorId(clienteDeTeste.getId_cliente());
        
        assertNotNull(clienteEncontrado, "Deveria ter encontrado um cliente com o ID fornecido.");
        assertEquals("Cliente de Teste JUnit", clienteEncontrado.getNome(), "O nome do cliente encontrado está incorreto.");
    }
    
    @AfterAll
    static void tearDown() {
        System.out.println("Executando limpeza final...");
        // Garante que o cliente de teste seja removido do banco de dados
        if (clienteDeTeste.getId_cliente() > 0) {
            clienteDAO.removerPorId(clienteDeTeste.getId_cliente());
            System.out.println("Cliente de teste removido com sucesso.");
        }
    }
}