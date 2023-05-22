package br.edu.fateczl.SpringMVCJasper.model;

import java.time.LocalDateTime;

public class MedicoPaciente {
	private String nomePaciente;
	private String nomeMedico;
	private LocalDateTime dataHora;
	private String cid;
	private String tratamento;

	public String getNomePaciente() {
		return nomePaciente;
	}

	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}

	public String getNomeMedico() {
		return nomeMedico;
	}

	public void setNomeMedico(String nomeMedico) {
		this.nomeMedico = nomeMedico;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getTratamento() {
		return tratamento;
	}

	public void setTratamento(String tratamento) {
		this.tratamento = tratamento;
	}

	@Override
	public String toString() {
		return "MedicoPaciente [nomePaciente=" + nomePaciente + ", nomeMedico=" + nomeMedico + ", dataHora=" + dataHora
				+ ", cid=" + cid + ", tratamento=" + tratamento + "]";
	}
}
