// O pacote para os testes deve espelhar o pacote do código principal.
package com.petshop.dao;

// Imports do JUnit 5.
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;


// Imports das suas classes.
import com.petshop.dados.Cliente;
import java.util.List;

/**
 * Classe de teste para a classe ClienteDAO.
 * A anotação @TestMethodOrder garante que os testes rodem em uma ordem específica.
 * A anotação @AfterAll garante que os dados de teste sejam limpos no final.
 */
@TestMethodOrder(OrderAnnotation.class)
class ClienteDAOTest {

    // Instancia o DAO e o objeto de teste como estáticos para serem usados
    // nos métodos @BeforeAll e @AfterAll.
    private static ClienteDAO clienteDAO = new ClienteDAO();
    private static Cliente clienteDeTeste = new Cliente();

    /**
     * Este método, anotado com @BeforeAll, é executado UMA VEZ antes de todos os testes.
     * É ideal para preparar o ambiente, como criar o objeto de teste.
     */
    @BeforeAll
    static void setUp() {
        System.out.println("Iniciando a suíte de testes para ClienteDAO...");
        // Configura o objeto de teste que será usado em todos os métodos.
        clienteDeTeste.setCpf("99999999999");
        clienteDeTeste.setNome("Cliente de Teste JUnit");
        clienteDeTeste.setTelefone("47999999999");
    }

    /**
     * TESTE 1: Verifica se um novo cliente pode ser adicionado.
     * A anotação @Test marca este método como um caso de teste executável.
     * @Order(1) garante que este teste rode primeiro.
     */
    @Test
    @Order(1)
    void testAdicionar() {
        System.out.println("Executando testeAdicionar...");
        // Ação: Chama o método que queremos testar.
        clienteDAO.adicionar(clienteDeTeste);
        
        // A verificação será feita no próximo teste para garantir que o dado foi persistido.
        // O teste passa se nenhuma exceção for lançada.
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

        // Procura pelo nosso cliente de teste na lista e guarda seu ID gerado pelo banco.
        boolean clienteEncontrado = false;
        for (Cliente c : clientes) {
            if (c.getCpf().equals(clienteDeTeste.getCpf())) {
                clienteEncontrado = true;
                clienteDeTeste.setId_cliente(c.getId_cliente()); // Guarda o ID para os próximos testes
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
        // Pré-condição: Garante que temos um ID válido do teste anterior.
        assertTrue(clienteDeTeste.getId_cliente() > 0, "O ID do cliente de teste é inválido.");

        // Ação: Busca o cliente pelo ID.
        Cliente clienteEncontrado = clienteDAO.buscarPorId(clienteDeTeste.getId_cliente());
        
        // Verificação: O cliente encontrado não pode ser nulo e deve ter o nome correto.
        assertNotNull(clienteEncontrado, "Deveria ter encontrado um cliente com o ID fornecido.");
        assertEquals("Cliente de Teste JUnit", clienteEncontrado.getNome(), "O nome do cliente encontrado está incorreto.");
    }
    
    /**
     * Este método, anotado com @AfterAll, é executado UMA VEZ depois de todos os testes.
     * É o lugar perfeito para limpar qualquer dado que os testes criaram.
     */
    @AfterAll
    static void tearDown() {
        System.out.println("Executando limpeza final...");
        // Garante que o cliente de teste seja removido do banco de dados,
        // não importa se os testes passaram ou falharam.
        if (clienteDeTeste.getId_cliente() > 0) {
            clienteDAO.removerPorId(clienteDeTeste.getId_cliente());
            System.out.println("Cliente de teste removido com sucesso.");
        }
    }
}