package br.edu.fateczl.SpringMVCJasper.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.edu.fateczl.SpringMVCJasper.model.Medico;

@Repository
public class MedicoDao implements IMedicoDao {

	@Autowired
	GenericDao gDao;

	@Override
	public List<Medico> listaMedicos() throws SQLException, ClassNotFoundException {
		List<Medico> medicos = new ArrayList<>();
		Connection c = gDao.getConnection();
		String sql = "SELECT id, nome FROM medico";

		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			Medico medico = new Medico();
			medico.setId(rs.getInt("id"));
			medico.setNome(rs.getString("nome"));
			medicos.add(medico);
		}

		rs.close();
		ps.close();
		c.close();
		return medicos;
	}
}
