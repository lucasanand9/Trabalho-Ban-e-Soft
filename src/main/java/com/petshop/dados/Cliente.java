package com.petshop.dados;

public class Cliente {
		private long id_cliente;
		private String nome;
		private String cpf;
		private String telefone;
		
		public Cliente() {}
		
		public Cliente(long id_cliente, String nome, String cpf, String telefone) {
			this.id_cliente = id_cliente;
			this.nome = nome;
			this.cpf = cpf;
			this.telefone = telefone;
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

		public String getCpf() {
			return cpf;
		}

		public void setCpf(String cpf) {
			this.cpf = cpf;
		}

		public String getTelefone() {
			return telefone;
		}

		public void setTelefone(String telefone) {
			this.telefone = telefone;
		}

		@Override
		public String toString() {
			return "Cliente [id_cliente=" + id_cliente + ", nome=" + nome + ", cpf=" + cpf + ", telefone=" + telefone
					+ "]";
		}
		
		
		
		
}
