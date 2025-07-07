package com.petshop.dados;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Fornecimento {
	private long id_fornecimento;
	private long id_fornecedor;
	private long id_produto;
	private LocalDate data;
	private int quantidade;
	private BigDecimal valor_total;
	
	public Fornecimento() {}
	
	public Fornecimento(long id_fornecimento, long id_fornecedor, long id_produto, LocalDate data, int quantidade, BigDecimal valor_total) {
		super();
		this.id_fornecimento = id_fornecimento;
		this.id_fornecedor = id_fornecedor;
		this.id_produto = id_produto;
		this.data = data;
		this.quantidade = quantidade;
		this.valor_total = valor_total;
	}

	public long getId_fornecimento() {
		return id_fornecimento;
	}

	public void setId_fornecimento(long id_fornecimento) {
		this.id_fornecimento = id_fornecimento;
	}

	public long getId_fornecedor() {
		return id_fornecedor;
	}

	public void setId_fornecedor(long id_fornecedor) {
		this.id_fornecedor = id_fornecedor;
	}

	public long getId_produto() {
		return id_produto;
	}

	public void setId_produto(long id_produto) {
		this.id_produto = id_produto;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValor_total() {
		return valor_total;
	}

	public void setValor_total(BigDecimal valor_total) {
		this.valor_total = valor_total;
	}

	@Override
	public String toString() {
		return "Fornecimento [id_fornecimento=" + id_fornecimento + ", id_fornecedor=" + id_fornecedor + ", id_produto="
				+ id_produto + ", data=" + data + ", quantidade=" + quantidade + ", valor_total=" + valor_total + "]";
	}
	
	
	
}
