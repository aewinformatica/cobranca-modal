package com.aewinformatica.cobranca.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.aewinformatica.cobranca.model.StatusTitulo;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

@Controller
@RequestMapping("/relatorios")
public class RelatoriosController {
	
	@Autowired
	private DataSource dataSource;
	
	@GetMapping
//	@GetMapping("/titulosEmitidos")
	public ModelAndView relatorioTitulosEmitidos() {
		ModelAndView mv = new ModelAndView("relatorio/RelatorioTitulosEmitidos");
		return mv;
	}
	
	@PostMapping
	public void imprimir(@RequestParam Map<String, Object> parametros,String status, HttpServletResponse response) throws JRException, SQLException, IOException {
		
		parametros = parametros == null ? parametros = new HashMap<>() : parametros;
		parametros.put("PARAM_STATUS", status);
		System.out.println(parametros);
		
		// Pega o arquivo .jasper localizado em resources
		InputStream jasperStream = this.getClass().getResourceAsStream("/relatorios/relatorio_titulos.jasper");
		
		Connection con = this.dataSource.getConnection();
		
		// Cria o objeto JaperReport com o Stream do arquivo jasper
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
		// Passa para o JasperPrint o relatório, os parâmetros e a fonte dos dados, no caso uma conexão ao banco de dados
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, con);

		// Configura a respota para o tipo PDF
		response.setContentType("application/pdf");
		// Define que o arquivo pode ser visualizado no navegador e também nome final do arquivo
		// para fazer download do relatório troque 'inline' por 'attachment'
		response.setHeader("Content-Disposition", "inline; filename=livros.pdf");

		// Faz a exportação do relatório para o HttpServletResponse
		final OutputStream outStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
	}
	

	@ModelAttribute("todosStatusTitulo")
	public List<StatusTitulo> todosStatusiTitulo() {

		return Arrays.asList(StatusTitulo.values());
	}
	
	@ModelAttribute("now")
	public LocalDateTime dataHora() {
		
		return LocalDateTime.now();
	}
}
