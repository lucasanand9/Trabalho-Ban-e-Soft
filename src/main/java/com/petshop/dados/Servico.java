package com.petshop.dados;

import java.math.BigDecimal;

public class Servico {
	private long id_servico;
	private String tipo;
	private BigDecimal preco;
	private long duracao; //em minutos 
	
	public Servico() {}
	
	public Servico(long id_servico, String tipo, BigDecimal preco, long duracao) {
		this.id_servico = id_servico;
		this.tipo = tipo;
		this.preco = preco;
		this.duracao = duracao;
	}

	public long getId_servico() {
		return id_servico;
	}

	public void setId_servico(long id_servico) {
		this.id_servico = id_servico;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public long getDuracao() {
		return duracao;
	}

	public void setDuracao(long duracao) {
		this.duracao = duracao;
	}

	@Override
	public String toString() {
		return "Servico [id_servico=" + id_servico + ", tipo=" + tipo + ", preco=" + preco + ", duracao=" + duracao
				+ "]";
	}
	
	
	
}
