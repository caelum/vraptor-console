package br.com.caelum.vraptor.console.myproducts;

import java.util.Arrays;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class HelloController {
	
	private final Result result;

	public HelloController(Result result) {
		this.result = result;
	}
	
	@Path("/")
	public void index() {
		System.out.println("Executing hello#index 50");
		result.include("list", Arrays.asList("string1", "string2"));
	}

}
