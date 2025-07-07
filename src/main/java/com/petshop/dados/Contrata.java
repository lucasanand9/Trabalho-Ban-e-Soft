package com.petshop.dados;

public class Contrata {
	private long id_animal;
	private long id_atendimento;
	
	public Contrata() {}
	
	public Contrata(long id_animal, long id_atendimento) {
		super();
		this.id_animal = id_animal;
		this.id_atendimento = id_atendimento;
	}

	public long getId_animal() {
		return id_animal;
	}

	public void setId_animal(long id_animal) {
		this.id_animal = id_animal;
	}

	public long getId_atendimento() {
		return id_atendimento;
	}

	public void setId_atendimento(long id_atendimento) {
		this.id_atendimento = id_atendimento;
	}

	@Override
	public String toString() {
		return "Contrata [id_animal=" + id_animal + ", id_atendimento=" + id_atendimento + "]";
	}
	
}
