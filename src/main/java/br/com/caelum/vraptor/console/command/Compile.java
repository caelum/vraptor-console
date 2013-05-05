package br.com.caelum.vraptor.console.command;

import com.google.inject.Inject;


public class Compile extends MavenCommand {
	
	@Inject
	public Compile(Maven maven) {
		super(maven, "compile");
	}

}
