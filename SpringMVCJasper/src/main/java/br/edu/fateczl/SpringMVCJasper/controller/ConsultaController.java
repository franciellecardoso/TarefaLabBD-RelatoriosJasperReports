package br.edu.fateczl.SpringMVCJasper.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.edu.fateczl.SpringMVCJasper.model.Medico;
import br.edu.fateczl.SpringMVCJasper.model.Paciente;
import br.edu.fateczl.SpringMVCJasper.persistence.ConsultaDao;
import br.edu.fateczl.SpringMVCJasper.persistence.GenericDao;
import br.edu.fateczl.SpringMVCJasper.persistence.MedicoDao;
import br.edu.fateczl.SpringMVCJasper.persistence.PacienteDao;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;

@Controller
public class ConsultaController {

	@Autowired
	GenericDao gDao;

	@Autowired
	MedicoDao mDao;

	@Autowired
	PacienteDao pDao;

	@Autowired
	ConsultaDao cDao;

	@RequestMapping(name = "consulta", value = "/consulta", method = RequestMethod.GET)
	public ModelAndView init(ModelMap model) {
		List<Medico> medicos = new ArrayList<>();
		List<Paciente> pacientes = new ArrayList<>();

		String saida = "";
		String erro = "";

		try {
			medicos = mDao.listaMedicos();
			pacientes = pDao.listaPaciente();

			System.out.println(medicos);
			System.out.println(pacientes);
		} catch (SQLException | ClassNotFoundException e) {
			erro = e.getMessage();
		} finally {
			model.addAttribute("medicos", medicos);
			model.addAttribute("pacientes", pacientes);
			model.addAttribute("saida", saida);
			model.addAttribute("erro", erro);
		}

		return new ModelAndView("consulta");
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(name = "relatorio", value = "/relatorio", method = RequestMethod.POST)
	public ResponseEntity geraRelatorio(@RequestParam Map<String, String> allParam, ModelMap model) {

		String botao = allParam.get("botao");

		
		String paciente = allParam.get("paciente");
		String medico = allParam.get("medico");
		String cid = allParam.get("cid");
		String tratamento = allParam.get("tratamento");

		String saida = "";
		String erro = "";

		// Definindo elementos que serão passados como parametros para o Jasper
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("tratamento", tratamento);

		byte[] bytes = null;

		System.out.println(tratamento);
		// Inicializando elementos do response
		InputStreamResource resource = null;
		HttpStatus status = null;
		HttpHeaders header = new HttpHeaders();

		try {
			if (botao.equalsIgnoreCase("gerar")) {
				int CID = Integer.parseInt(cid);
				saida = cDao.inserirConsulta(paciente, medico, CID, tratamento);
				System.out.println(paciente + " " + medico + " " + CID + " " + tratamento);

				Connection conn = gDao.getConnection();
				System.out.println(conn);
				File arquivo = ResourceUtils.getFile("classpath:reports/relatorioConsulta.jasper");
				System.out.println(arquivo);
				JasperReport report = (JasperReport) JRLoader.loadObjectFromFile(arquivo.getAbsolutePath());
				System.out.println(report);
				bytes = JasperRunManager.runReportToPdf(report, param, conn);
				System.out.println(bytes);
				System.out.println(bytes.length);
			}
		} catch (FileNotFoundException | JRException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			erro = e.getMessage();
			status = HttpStatus.BAD_REQUEST;
		} finally {
			model.addAttribute("saida", saida);
			model.addAttribute("erro", erro);
			if (erro.equals("")) {
				InputStream inputStream = new ByteArrayInputStream(bytes);
				System.out.println(inputStream);
				resource = new InputStreamResource(inputStream);
				header.setContentLength(bytes.length);
				header.setContentType(MediaType.APPLICATION_PDF);
				status = HttpStatus.OK;
			}
		}

		return new ResponseEntity(resource, header, status);
	}
}
