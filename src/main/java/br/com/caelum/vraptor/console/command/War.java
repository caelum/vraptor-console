package br.com.caelum.vraptor.console.command;

import com.google.inject.Inject;


public class War extends MavenCommand {

	@Inject
	public War(Maven maven) {
		super(maven, "war");
	}

}
