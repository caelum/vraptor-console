package br.com.caelum.vraptor.console.myproducts;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;

@Resource
public class HelloController {
	
	@Path("/")
	public void index() {
		System.out.println("Executing hello#index 333");
	}

}
