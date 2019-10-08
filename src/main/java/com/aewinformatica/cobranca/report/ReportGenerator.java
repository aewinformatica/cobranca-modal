package com.aewinformatica.cobranca.report;

import com.aewinformatica.cobranca.dto.StatusTituloDTO;

public interface ReportGenerator {

	public byte[] gerarRelatorioTitulos(StatusTituloDTO statusTituloDTO) throws Exception;
}
