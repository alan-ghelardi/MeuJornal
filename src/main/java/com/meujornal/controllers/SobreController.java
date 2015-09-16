package com.meujornal.controllers;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;

/**
 * Controller responsável pela(s) página(s) de informação sobre o sistema.
 * 
 */

@Controller
public class SobreController {
	
	// Redireciona à página de informações sobre o sistema
	@Get("/sobre")
	public void index() {
	}

}
