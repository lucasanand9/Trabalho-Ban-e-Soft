package com.petshop.dao;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.petshop.dados.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;


class CompraDAOTest {
    private static CompraDAO compraDAO = new CompraDAO();
    private static ClienteDAO clienteDAO = new ClienteDAO();
    private static ProdutoDAO produtoDAO = new ProdutoDAO();
    private static Cliente clienteTeste;
    private static Produto produtoTeste;
    private static Compra compraTeste;

    @BeforeAll
    static void setUp() {
        clienteTeste = new Cliente();
        clienteTeste.setCpf("55555555555");
        clienteTeste.setNome("Cliente para Compra Teste");
        clienteDAO.adicionar(clienteTeste);
        clienteTeste.setId_cliente(clienteDAO.listarTodos().stream().filter(c -> c.getCpf().equals("55555555555")).findFirst().get().getId_cliente());
        produtoTeste = new Produto();
        produtoTeste.setNome("Brinquedo de Teste");
        produtoTeste.setValor(new BigDecimal("25.00"));
        produtoDAO.adicionar(produtoTeste);
        produtoTeste.setId_produto(produtoDAO.listarTodos().stream().filter(p -> p.getNome().equals("Brinquedo de Teste")).findFirst().get().getId_produto());

    }

    @Test
    void testRegistrarCompra() {
        compraTeste = new Compra();
        compraTeste.setId_cliente(clienteTeste.getId_cliente());
        compraTeste.setId_produto(produtoTeste.getId_produto());
        compraTeste.setQuantidade(2);
        compraTeste.setValor_total(new BigDecimal("50.00")); // 2 * 25.00
        compraTeste.setData(LocalDate.now());
        compraTeste.setHora(LocalTime.now());
        compraDAO.adicionar(compraTeste);
        compraTeste.setId_compra(compraDAO.listarTodos().stream().filter(c -> c.getId_cliente() == clienteTeste.getId_cliente()).findFirst().get().getId_compra());
        assertTrue(compraTeste.getId_compra() > 0, "A compra não foi registrada corretamente (sem ID).");
    }

    @AfterAll
    static void tearDown() {
        if (compraTeste != null && compraTeste.getId_compra() > 0) {
            compraDAO.removerPorId(compraTeste.getId_compra());
        }
        if (produtoTeste != null && produtoTeste.getId_produto() > 0) {
            produtoDAO.removerPorId(produtoTeste.getId_produto());
        }
        if (clienteTeste != null && clienteTeste.getId_cliente() > 0) {
            clienteDAO.removerPorId(clienteTeste.getId_cliente());
        }
    }


}