package com.petshop.dao;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import com.petshop.dados.Produto;
import java.math.BigDecimal;
import java.util.List;

@TestMethodOrder(OrderAnnotation.class)
class ProdutoDAOTest {

    private static ProdutoDAO produtoDAO = new ProdutoDAO();
    private static Produto produtoDeTeste = new Produto();

    @BeforeAll
    static void setUp() {
        produtoDeTeste.setNome("Ração Teste Super Premium");
        produtoDeTeste.setTipo("Alimento");
        produtoDeTeste.setValor(new BigDecimal("150.75"));
    }

    @Test
    @Order(1)
    void testAdicionarProduto() {
        produtoDAO.adicionar(produtoDeTeste);
    }

    @Test
    @Order(2)
    void testListarEEncontrarProduto() {
        List<Produto> produtos = produtoDAO.listarTodos();
        boolean encontrado = false;
        for (Produto p : produtos) {
            if (p.getNome().equals(produtoDeTeste.getNome())) {
                encontrado = true;
                produtoDeTeste.setId_produto(p.getId_produto());
                break;
            }
        }
        assertTrue(encontrado, "O produto de teste deveria ter sido encontrado na lista.");
    }

    @Test
    @Order(3)
    void testRemoverProduto() {
        assertTrue(produtoDeTeste.getId_produto() > 0, "ID do produto de teste inválido para remoção.");
        produtoDAO.removerPorId(produtoDeTeste.getId_produto());
        Produto produtoRemovido = produtoDAO.buscarPorId(produtoDeTeste.getId_produto());
        assertNull(produtoRemovido, "O produto não deveria ser encontrado após a remoção.");
    }

    @AfterAll
    static void tearDown() {
        if (produtoDeTeste.getId_produto() > 0) {
            produtoDAO.removerPorId(produtoDeTeste.getId_produto());
        }
    }
}