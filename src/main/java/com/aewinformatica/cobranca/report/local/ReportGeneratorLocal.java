package com.aewinformatica.cobranca.report.local;

import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.aewinformatica.cobranca.report.ReportGenerator;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Profile("local")
@Component
public class ReportGeneratorLocal implements ReportGenerator {

	@Autowired
	private DataSource dataSource;
	
	@Override
	public byte[] gerarRelatorioTitulos(String status) throws Exception {

		
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
