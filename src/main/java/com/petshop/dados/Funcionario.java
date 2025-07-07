package com.petshop.dados;
	
import java.time.LocalDate;
import java.math.BigDecimal;

	
public class Funcionario {
	private long id_funcionario;
	private String cpf;
	private String nome;
	private BigDecimal salario;
	private LocalDate data_nascimento;
	
	public Funcionario() {}
	
	public Funcionario(long id_funcionario, String cpf, String nome, BigDecimal salario, LocalDate data_nascimento) {
		super();
		this.id_funcionario = id_funcionario;
		this.cpf = cpf;
		this.nome = nome;
		this.salario = salario;
		this.data_nascimento = data_nascimento;
	}
	
	public long getId_funcionario() {
		return id_funcionario;
	}
	
	public void setId_funcionario(long id_funcionario) {
		this.id_funcionario = id_funcionario;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	public LocalDate getData_nascimento() {
		return data_nascimento;
	}

	public void setData_nascimento(LocalDate data_nascimento) {
		this.data_nascimento = data_nascimento;
	}

	@Override
	public String toString() {
		return "Funcionario [id_funcionario=" + id_funcionario + ", cpf=" + cpf + ", nome=" + nome + ", salario="
				+ salario + ", data_nascimento=" + data_nascimento + "]";
	}
	
}
