package br.edu.fateczl.SpringMVCJasper.model;

import java.time.LocalDate;

public class Paciente {
	private int id;
	private String nome;
	private LocalDate dataNasc;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getdataNasc() {
		return dataNasc;
	}

	public void setDataNasc(LocalDate dataNasc) {
		this.dataNasc = dataNasc;
	}

	@Override
	public String toString() {
		return "Paciente [id=" + id + ", nome=" + nome + ", dataNasc=" + dataNasc + "]";
	}
}
