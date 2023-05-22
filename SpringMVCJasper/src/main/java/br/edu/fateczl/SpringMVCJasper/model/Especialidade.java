package br.edu.fateczl.SpringMVCJasper.model;

public class Especialidade {

	private int id;
	private String especialidade;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	@Override
	public String toString() {
		return "Especialidade [id=" + id + ", especialidade=" + especialidade + "]";
	}
}
