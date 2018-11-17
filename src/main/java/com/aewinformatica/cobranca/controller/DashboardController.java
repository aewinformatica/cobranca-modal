package com.aewinformatica.cobranca.controller;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/")
public class DashboardController {

	@GetMapping
	public ModelAndView home(){
		ModelAndView mv = new ModelAndView("LayoutPadrao");
		mv.addObject("now", LocalDateTime.now());
		
		return new ModelAndView ("redirect:/titulos");
	}
	

}
