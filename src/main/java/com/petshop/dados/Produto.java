package com.petshop.dados;

import java.math.BigDecimal;

public class Produto {
	private long id_produto;
	private String nome;
	private String tipo;
	private BigDecimal valor;
	
	public Produto() {}
	
	public Produto(long id_produto, String nome, String tipo, BigDecimal valor) {
		this.id_produto = id_produto;
		this.nome = nome;
		this.tipo = tipo;
		this.valor = valor;
	}
	
	public long getId_produto() {
		return id_produto;
	}
	public void setId_produto(long id_produto) {
		this.id_produto = id_produto;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "Produto [id_produto=" + id_produto + ", nome=" + nome + ", tipo=" + tipo + ", valor=" + valor + "]";
	}
	
	
	
	
}
