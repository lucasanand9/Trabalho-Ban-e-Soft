package com.petshop.dados;

import java.time.LocalDate;
import java.time.LocalTime;
import java.math.BigDecimal;

public class Atendimento {
	private long id_atendimento;
	private long id_funcionario;
	private long id_servico;
	private BigDecimal valor_atendimento;
	private LocalDate data;
	private LocalTime hora;
	
	public Atendimento() {}
	
	public Atendimento(long id_atendimento, long id_funcionario, long id_servico, BigDecimal valor_atendimento, LocalDate data, LocalTime hora) {
		super();
		this.id_atendimento = id_atendimento;
		this.id_funcionario = id_funcionario;
		this.id_servico = id_servico;
		this.valor_atendimento = valor_atendimento;
		this.data = data;
		this.hora = hora;
	}

	public long getId_atendimento() {
		return id_atendimento;
	}

	public void setId_atendimento(long id_atendimento) {
		this.id_atendimento = id_atendimento;
	}

	public long getId_funcionario() {
		return id_funcionario;
	}

	public void setId_funcionario(long id_funcionario) {
		this.id_funcionario = id_funcionario;
	}

	public long getId_servico() {
		return id_servico;
	}

	public void setId_servico(long id_servico) {
		this.id_servico = id_servico;
	}

	public BigDecimal getValor_atendimento() {
		return valor_atendimento;
	}

	public void setValor_atendimento(BigDecimal valor_atendimento) {
		this.valor_atendimento = valor_atendimento;
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

	@Override
	public String toString() {
		return "Atendimento [id_atendimento=" + id_atendimento + ", id_funcionario=" + id_funcionario + ", id_servico="
				+ id_servico + ", valor_atendimento=" + valor_atendimento + ", data=" + data + ", hora=" + hora + "]";
	}
	
	
}
