package com.aewinformatica.cobranca.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.aewinformatica.cobranca.dto.StatusTituloDTO;
import com.aewinformatica.cobranca.model.StatusTitulo;
import com.aewinformatica.cobranca.report.ReportGenerator;

@Controller
@RequestMapping("/relatorios")
public class RelatoriosController {
	
	@Autowired
	private ReportGenerator reportGenerator;
	
	@GetMapping("/titulosEmitidos")
	public ModelAndView relatorioTitulosEmitidos() {
		ModelAndView mv = new ModelAndView("relatorio/RelatorioTitulosEmitidos");
//		mv.addObject(new StatusTituloDTO());
		return mv;
	}
	
	@PostMapping("/titulosEmitidos")
	public ResponseEntity<byte[]> gerarRelatorioTitulosEmitidos(StatusTituloDTO statusTituloDTO) throws Exception {
		byte[] relatorio = reportGenerator.gerarRelatorioTitulos(statusTituloDTO); 
		
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE)
				.body(relatorio);
	}
	
	@ModelAttribute("todosStatusTitulo")
	public List<StatusTitulo> todosStatusiTitulo() {

		return Arrays.asList(StatusTitulo.values());
	}
}
