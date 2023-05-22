package br.edu.fateczl.SpringMVCJasper.persistence;

import java.sql.SQLException;

public interface IConsultaDao {
	public String inserirConsulta(String nome_paciente, String nome_medico, int cid, String tratamento) throws SQLException, ClassNotFoundException;
}
