package br.edu.fateczl.SpringMVCJasper.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.edu.fateczl.SpringMVCJasper.model.Paciente;

@Repository
public class PacienteDao implements IPacienteDao {

	@Autowired
	GenericDao gDao;

	@Override
	public List<Paciente> listaPaciente() throws SQLException, ClassNotFoundException {
		List<Paciente> pacientes = new ArrayList<>();
		Connection c = gDao.getConnection();
		String sql = "SELECT id, nome, dataNasc FROM paciente";

		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			Paciente paciente = new Paciente();
			paciente.setId((rs.getInt("id")));
			paciente.setNome(rs.getString("nome"));
			paciente.setDataNasc(LocalDate.parse(rs.getString("dataNasc")));
			pacientes.add(paciente);
		}
		c.close();
		ps.close();
		rs.close();

		return pacientes;
	}
}
