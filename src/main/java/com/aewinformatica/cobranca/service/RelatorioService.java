package com.aewinformatica.cobranca.service;

import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aewinformatica.cobranca.dto.StatusTituloDTO;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Service
public class RelatorioService {

	@Autowired
	private DataSource dataSource;
	
	public byte[] gerarRelatorioTitulos(StatusTituloDTO statusTituloDTO) throws Exception {

		String status = statusTituloDTO.getStatus().getDescricao();
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("format", "pdf");
		parametros.put("PARAM_STATUS", status);
		
		
		InputStream inputStream = this.getClass()
				.getResourceAsStream("/relatorios/relatorio_titulos.jasper");
		
		Connection con = this.dataSource.getConnection();
		
		try {
			JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros, con);
			return JasperExportManager.exportReportToPdf(jasperPrint);
		} finally {
			con.close();
		}
	}
}
