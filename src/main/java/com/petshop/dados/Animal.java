package com.petshop.dados;

public class Animal {
	private long id_animal;
	private long id_cliente;
	private String nome;
	private String raca;
	private int idade;
	private String tamanho;
	
	public Animal() {}
	
	public Animal(long id_animal, long id_cliente, String nome, String raca, int idade, String tamanho) {
		this.id_animal = id_animal;
		this.id_cliente = id_cliente;
		this.nome = nome;
		this.raca = raca;
		this.idade = idade;
		this.tamanho = tamanho;
	}

	public long getId_animal() {
		return id_animal;
	}

	public void setId_animal(long id_animal) {
		this.id_animal = id_animal;
	}

	public long getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(long id_cliente) {
		this.id_cliente = id_cliente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRaca() {
		return raca;
	}

	public void setRaca(String raca) {
		this.raca = raca;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getTamanho() {
		return tamanho;
	}

	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}

	@Override
	public String toString() {
		return "Animal [id_animal=" + id_animal + ", id_cliente=" + id_cliente + ", nome=" + nome + ", raca=" + raca
				+ ", idade=" + idade + ", tamanho=" + tamanho + "]";
	}
	
	
	
	
}
