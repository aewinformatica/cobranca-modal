package com.aewinformatica.cobranca.controller;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aewinformatica.cobranca.model.StatusTitulo;
import com.aewinformatica.cobranca.model.Titulo;
import com.aewinformatica.cobranca.repository.filtro.TituloFilter;
import com.aewinformatica.cobranca.service.CadastroTituloService;


@Controller
@RequestMapping("titulos")
public class TituloController {
	
	@Autowired
	private CadastroTituloService cadastroTituloService;

	private static String VIEW = "titulos/Titulos";

	@RequestMapping("/novo")
	public ModelAndView novo(Titulo titulo) {

		ModelAndView mv = new ModelAndView(VIEW);
//		mv.addObject("now", LocalDateTime.now());
		
		return mv;
	}

	@GetMapping
	public ModelAndView pesquisar(@ModelAttribute("filtro") TituloFilter filtro, Titulo titulo) {
		List<Titulo> todosTitulos = cadastroTituloService.filtrar(filtro);
		
		ModelAndView mv = new ModelAndView(VIEW);
		mv.addObject("titulos", todosTitulos);
//		mv.addObject("now", LocalDateTime.now());
		return mv;
	}
	
	@PostMapping(value= {"/novo", "{\\d+}"})
	public ModelAndView salvar(@Validated Titulo titulo, Errors errors, RedirectAttributes attributes) {

		if (errors.hasErrors()) {

			return novo(titulo);
		}
		
		cadastroTituloService.salvar(titulo);
		attributes.addFlashAttribute("mensagem", "Titulo salvo com sucesso!");

		return new ModelAndView ("redirect:/titulos");
	}
	
	@RequestMapping("{codigo}")
	public ModelAndView edicao(@PathVariable("codigo") Titulo titulo){
		
		ModelAndView mv =  novo(titulo);
		mv.addObject(titulo);
//		mv.addObject("now", LocalDateTime.now());
		return mv;
		
	}
	
	@RequestMapping(value="{codigo}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long codigo, RedirectAttributes attributes){
		
		cadastroTituloService.excluir(codigo);
		attributes.addFlashAttribute("mensagem","Titulo excluido com sucesso!");
		return "redirect:/titulos"; 
	}

	@RequestMapping(value = "/{codigo}/receber", method = RequestMethod.PUT)
	public @ResponseBody String receber(@PathVariable Long codigo) {
		return cadastroTituloService.receber(codigo);
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
