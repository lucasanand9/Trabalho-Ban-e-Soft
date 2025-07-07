package com.petshop.dados;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class Compra {
	private long id_compra;
	private long id_cliente;
	private long id_produto;
	private LocalDate data;
	private LocalTime hora;
	private int quantidade;
	private BigDecimal valor_total;
	
	public Compra() {}
	
	public Compra(long id_compra, long id_cliente, long id_produto, LocalDate data, LocalTime hora, int quantidade, BigDecimal valor_total) {
		super();
		this.id_compra = id_compra;
		this.id_cliente = id_cliente;
		this.id_produto = id_produto;
		this.data = data;
		this.hora = hora;
		this.quantidade = quantidade;
		this.valor_total = valor_total;
	}

	public long getId_compra() {
		return id_compra;
	}

	public void setId_compra(long id_compra) {
		this.id_compra = id_compra;
	}

	public long getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(long id_cliente) {
		this.id_cliente = id_cliente;
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

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
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
		return "Compra [id_compra=" + id_compra + ", id_cliente=" + id_cliente + ", id_produto=" + id_produto
				+ ", data=" + data + ", hora=" + hora + ", quantidade=" + quantidade + ", valor_total=" + valor_total
				+ "]";
	}
	
	
	
	
}
