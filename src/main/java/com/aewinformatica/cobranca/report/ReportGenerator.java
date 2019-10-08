package com.aewinformatica.cobranca.report;

public interface ReportGenerator {

	public byte[] gerarRelatorioTitulos(String status) throws Exception;
}
