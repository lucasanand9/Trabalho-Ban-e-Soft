package com.petshop.dados;

public class Fornecedor {
	private long id_fornecedor;
	private String cnpj;
	private String nome;
	private String cep;
	private String complemento;
	private String numero;

	public Fornecedor() {}

	public Fornecedor(long id_fornecedor, String cnpj, String nome, String cep, String complemento, String numero) {
		this.id_fornecedor = id_fornecedor;
		this.cnpj = cnpj;
		this.nome = nome;
		this.cep = cep;
		this.complemento = complemento;
		this.numero = numero;
	}
	public long getId_fornecedor() {
		return id_fornecedor;
	}

	public void setId_fornecedor(long id_fornecedor) {
		this.id_fornecedor = id_fornecedor;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@Override
	public String toString() {
		return "Fornecedor [id_fornecedor=" + id_fornecedor + ", cnpj=" + cnpj + ", nome=" + nome + ", cep=" + cep
				+ ", complemento=" + complemento + ", numero=" + numero + "]";
	}
	
	
	
	
}
