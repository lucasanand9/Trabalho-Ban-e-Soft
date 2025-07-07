package com.petshop.dados;

public class Ocorrencia {
	private long id_ocorrencia;
	private long id_atendimento;
	private String descricao;
	
	public Ocorrencia() {}
	
	public Ocorrencia(long id_ocorrencia, long id_atendimento, String descricao) {
		super();
		this.id_ocorrencia = id_ocorrencia;
		this.id_atendimento = id_atendimento;
		this.descricao = descricao;
	}

	public long getId_ocorrencia() {
		return id_ocorrencia;
	}

	public void setId_ocorrencia(long id_ocorrencia) {
		this.id_ocorrencia = id_ocorrencia;
	}

	public long getId_atendimento() {
		return id_atendimento;
	}

	public void setId_atendimento(long id_atendimento) {
		this.id_atendimento = id_atendimento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "Ocorrencia [id_ocorrencia=" + id_ocorrencia + ", id_atendimento=" + id_atendimento + ", descricao="
				+ descricao + "]";
	}
	
	
	
	
}
