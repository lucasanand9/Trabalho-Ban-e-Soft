package com.petshop.dao;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import com.petshop.dados.Animal;
import com.petshop.dados.Cliente;

@TestMethodOrder(OrderAnnotation.class)
class AnimalDAOTest {

    private static ClienteDAO clienteDAO = new ClienteDAO();
    private static AnimalDAO animalDAO = new AnimalDAO();
    private static Cliente donoDeTeste = new Cliente();
    private static Animal animalDeTeste = new Animal();

    @BeforeAll
    static void setUp() {
        donoDeTeste.setCpf("88888888888");
        donoDeTeste.setNome("Dono de Teste para Animal");
        donoDeTeste.setTelefone("47888888888");
        clienteDAO.adicionar(donoDeTeste);

        //pegar ID do dono
        for (Cliente c : clienteDAO.listarTodos()) {
            if (c.getCpf().equals(donoDeTeste.getCpf())) {
                donoDeTeste.setId_cliente(c.getId_cliente());
                break;
            }
        }
        
        animalDeTeste.setId_cliente(donoDeTeste.getId_cliente());
        animalDeTeste.setNome("Pet de Teste");
        animalDeTeste.setRaca("Vira-lata Caramelo");
        animalDeTeste.setIdade(2);
        animalDeTeste.setTamanho("Médio");
    }

    @Test
    @Order(1)
    void testAdicionarAnimal() {
        animalDAO.adicionar(animalDeTeste);

        Animal animalEncontrado = null;
        for (Animal a : animalDAO.listarTodos()) {
            if (a.getNome().equals("Pet de Teste") && a.getId_cliente() == donoDeTeste.getId_cliente()) {
                animalEncontrado = a;
                animalDeTeste.setId_animal(a.getId_animal()); // Guarda o ID para a remoção
                break;
            }
        }
        assertNotNull(animalEncontrado, "O animal de teste não foi encontrado no banco.");
        assertEquals(donoDeTeste.getId_cliente(), animalEncontrado.getId_cliente(), "O ID do dono no animal está incorreto.");
    }
    
    @AfterAll
    static void tearDown() {
        if (animalDeTeste.getId_animal() > 0) {
            animalDAO.removerPorId(animalDeTeste.getId_animal());
        }
        if (donoDeTeste.getId_cliente() > 0) {
            clienteDAO.removerPorId(donoDeTeste.getId_cliente());
        }
    }
}