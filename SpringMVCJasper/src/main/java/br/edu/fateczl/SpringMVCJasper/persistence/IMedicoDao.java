package br.edu.fateczl.SpringMVCJasper.persistence;

import java.sql.SQLException;
import java.util.List;

import br.edu.fateczl.SpringMVCJasper.model.Medico;

public interface IMedicoDao {
	public List<Medico> listaMedicos() throws SQLException, ClassNotFoundException;
}
