package br.edu.fateczl.SpringMVCJasper.persistence;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ConsultaDao implements IConsultaDao {

	@Autowired
	GenericDao gDao;

	@Override
	public String inserirConsulta(String nomePaciente, String nomeMedico, int cid, String tratamento)
			throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();

		String sp_sql = "{CALL sp_insere_cid_tratamento(?,?,?,?)}";

		CallableStatement cs = c.prepareCall(sp_sql);
		cs.setString(1, nomePaciente);
		cs.setString(2, nomeMedico);
		cs.setInt(3, cid);
		cs.setString(4, tratamento);
		cs.execute();
		System.out.println(nomePaciente + " " + nomeMedico + " " + cid + " " + tratamento);

		c.close();
		cs.close();
		return "Consulta Inserida";
	}
}
