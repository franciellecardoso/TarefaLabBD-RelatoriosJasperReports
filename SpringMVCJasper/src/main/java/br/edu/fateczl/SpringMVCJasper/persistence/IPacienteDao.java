package br.edu.fateczl.SpringMVCJasper.persistence;

import java.sql.SQLException;
import java.util.List;

import br.edu.fateczl.SpringMVCJasper.model.Paciente;

public interface IPacienteDao {
	public List<Paciente> listaPaciente() throws SQLException, ClassNotFoundException;
}
